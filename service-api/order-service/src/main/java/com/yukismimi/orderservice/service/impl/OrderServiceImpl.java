package com.yukismimi.orderservice.service.impl;

import com.yukismimi.common.exception.Asserts;
import com.yukismimi.common.utils.SecurityUtil;
import com.yukismimi.orderservice.client.AddressClient;
import com.yukismimi.orderservice.client.CartClient;
import com.yukismimi.orderservice.client.StockClient;
import com.yukismimi.orderservice.client.dto.Address;
import com.yukismimi.orderservice.client.dto.CartItem;
import com.yukismimi.orderservice.client.dto.Stock;
import com.yukismimi.orderservice.component.CancelOrderSender;
import com.yukismimi.orderservice.dto.ConfirmOrderDTO;
import com.yukismimi.orderservice.dto.OrderParamDTO;
import com.yukismimi.orderservice.dto.OrderResultDTO;
import com.yukismimi.orderservice.entity.Order;
import com.yukismimi.orderservice.entity.OrderItem;
import com.yukismimi.orderservice.repository.OrderItemRepository;
import com.yukismimi.orderservice.repository.OrderRepository;
import com.yukismimi.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    CartClient cartClient;

    @Autowired
    StockClient stockClient;

    @Autowired
    AddressClient addressClient;

    @Autowired
    CancelOrderSender cancelOrderSender;

    @Override
    public OrderResultDTO generateOrder(OrderParamDTO orderParamDTO) {

        String username = SecurityUtil.getUsername();
        Long userId = SecurityUtil.getUserId();

        //获取购物车信息
        List<CartItem> cartItemList = cartClient.getCartItems()
                .getData()
                .stream()
                .filter(cartItem -> cartItem.getChecked() == 1)
                .collect(Collectors.toList());


        //生成下单商品信息
        String orderSn = generateOrderSn();
        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartItem cartItem : cartItemList) {
            OrderItem orderItem = OrderItem.builder()
                    .orderId(0L)
                    .orderSn(orderSn)
                    .productId(cartItem.getProductId())
                    .productSn(cartItem.getProductSn())
                    .productSkuId(cartItem.getProductSkuId())
                    .productSkuCode(cartItem.getProductSkuCode())
                    .productQuantity(cartItem.getQuantity())
                    .productName(cartItem.getProductName())
                    .productCategoryId(cartItem.getProductCategoryId())
                    .productBrand(cartItem.getProductBrand())
                    .productAttr(cartItem.getProductAttr())
                    .productPic(cartItem.getProductPic())
                    .productPrice(cartItem.getPrice())
                    .sp1(cartItem.getSp1())
                    .sp2(cartItem.getSp2())
                    .sp3(cartItem.getSp3())
                    //部分功能暂不打算实现，以下设定固定值
                    .couponAmount(new BigDecimal(0))//不使用优惠券
                    .integrationAmount(new BigDecimal(0))//不使用积分
                    .promotionAmount(new BigDecimal(0))//本项目暂不加入优惠促销功能
                    .promotionName(null)//本项目暂不加入优惠促销功能
                    .realAmount(cartItem.getPrice())//本项目暂不加入优惠功能，实付金额故使用原价
                    .giftGrowth(0)//不计算成长值
                    .giftIntegration(0)//不计算积分
                    .build();
            orderItemList.add(orderItem);
        }

        //判断购物车库存
        if (!hasStock(orderItemList)) {
            Asserts.fail("库存不足，无法下单");
        }

        //锁定库存
        lockOrReleaseStock(orderItemList, true);

        //获取地址信息 ID暂定为1
        Address address = addressClient.getAddress(1L).getData();

        Order order = Order.builder()
                .userId(userId)
                .orderSn(orderSn)
                .createTime(Timestamp.valueOf(LocalDateTime.now()))
                .username(username)
                .totalAmount(calcTotalAmount(orderItemList))
                .payAmount(calcTotalAmount(orderItemList))
                //支付方式：0->未支付；1->支付宝；2->微信
                .payType(0)
                //订单来源：0->PC订单；1->app订单
                .sourceType(0)
                //订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
                .status(0)
                //订单类型：0->正常订单；1->秒杀订单
                .orderType(0)
                .autoConfirmDay(9999)
                .integration(0)
                .growth(0)
                .promotionInfo(null)
                //收货地址信息
                .receiverName(address.getName())
                .receiverPhone(address.getPhoneNumber())
                .receiverPostCode(address.getPostCode())
                .receiverProvince(address.getProvince())
                .receiverCity(address.getCity())
                .receiverRegion(address.getRegion())
                .receiverDetailAddress(address.getDetailAddress())
                .note(null)
                //0->未确认；1->已确认
                .confirmStatus(0)
                .deleteStatus(0)
                //发票信息 暂时不处理
                .billType(0)
                .billHeader(null)
                .billContent(null)
                .billReceiverEmail(null)
                .billReceiverPhone(null)
                .freightAmount(new BigDecimal(0))//不计算运费
                .promotionAmount(new BigDecimal(0))//不计算促销折扣
                .integrationAmount(new BigDecimal(0))//不计算积分折扣
                .couponAmount(new BigDecimal(0))//不计算优惠券折扣
                .discountAmount(new BigDecimal(0))//不计算管理员后台调整金额的折扣
                .build();

        Long orderId = orderRepository.save(order).getId();
        orderItemList.forEach(orderItem -> orderItem.setOrderId(orderId));
        orderItemRepository.saveAll(orderItemList);

        //如使用优惠券更新优惠券使用状态 暂无优惠券模块
        // TODO: 2020/4/2 coupon module null
        //如使用积分需要扣除积分 暂无积分模块
        // TODO: 2020/4/2 integration module null

        //删除购物车商品
        cartItemList.stream().map(CartItem::getId).map(cartClient::deleteCartItems);

        //发送延迟消息取消订单
        cancelOrderSender.sendMessage(orderId, 60 * 1000);

        //返回值
        OrderResultDTO orderResultDTO = OrderResultDTO.builder().order(order).orderItemList(orderItemList).build();
        return orderResultDTO;
    }

    @Override
    public ConfirmOrderDTO generateConfirmOrder() {

        List<Address> addressList = addressClient.listAddresses().getData();
        List<CartItem> cartItemList = cartClient.getCartItems().getData();
        BigDecimal totalAmount = calcCartItemsAmount(cartItemList);
        BigDecimal promotionAmount = new BigDecimal(0);
        BigDecimal freightAmount = new BigDecimal(0);

        return ConfirmOrderDTO.builder()
                .addressList(addressList)
                .cartItemList(cartItemList)
                .totalAmount(totalAmount)
                .payAmount(totalAmount) //暂未加入优惠模块
                .promotionAmount(promotionAmount)
                .freightAmount(freightAmount)
                .build();
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        //修改订单状态为取消
        order.setStatus(4);
        orderRepository.save(order);

        //释放锁定库存
        List<OrderItem> orderItemList = orderItemRepository.findAllByOrderId(orderId);
        lockOrReleaseStock(orderItemList, false);
        //修改优惠券使用状态
        // TODO: 2020/4/3 modify coupon status
        //返还使用积分
        // TODO: 2020/4/3 return integration
    }

    @Override
    public void pay(String orderNo) {
        // TODO: 2020/4/3 待实现 预计整合支付宝功能
    }

    @Override
    public List<Order> getAllOrder() {
        Long id = SecurityUtil.getUserId();
        List<Order> orderList = orderRepository.findAllByUserId(id);
        return orderList;
    }


    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }

    public String generateOrderSn() {
        return String.format("%s", System.currentTimeMillis());
    }

    //判断是否全部购物车清单物品均有库存
    private boolean hasStock(List<OrderItem> orderItemList) {
        List<Long> skuIdList = orderItemList.stream()
                .map(OrderItem::getProductSkuId)
                .collect(Collectors.toList());
        Map<Long, OrderItem> skuIdMap = orderItemList.stream().collect(Collectors.toMap(OrderItem::getProductSkuId, orderItem -> orderItem));
        return stockClient.getStock(skuIdList).getData()
                .stream()
                .allMatch(stock -> skuIdMap.get(stock.getId()).getProductQuantity() <= stock.getStock() - stock.getLockStock());
    }

    //锁定或释放锁定库存
    private void lockOrReleaseStock(List<OrderItem> orderItemList, boolean isLock) {
        List<Long> skuIdList = orderItemList.stream()
                .map(OrderItem::getProductSkuId)
                .collect(Collectors.toList());
        Map<Long, OrderItem> skuIdMap = orderItemList.stream().collect(Collectors.toMap(OrderItem::getProductSkuId, orderItem -> orderItem));
        List<Stock> stockList = stockClient.getStock(skuIdList).getData();
        if (isLock)
            stockList.forEach(stock -> stock.setLockStock(stock.getLockStock() + skuIdMap.get(stock.getId()).getProductQuantity()));
        else
            stockList.forEach(stock -> stock.setLockStock(stock.getLockStock() - skuIdMap.get(stock.getId()).getProductQuantity()));
        stockClient.modifyStock(stockList);
    }

    //计算全部金额
    private BigDecimal calcTotalAmount(List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .map(orderItem -> orderItem.getProductPrice().multiply(new BigDecimal(orderItem.getProductQuantity())))
                .reduce(BigDecimal::add)
                .orElseThrow();
    }

    //计算购物车中商品金额
    private BigDecimal calcCartItemsAmount(List<CartItem> cartItemList) {
        return cartItemList.stream()
                .map(cartItem -> cartItem.getPrice().multiply(new BigDecimal(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElseThrow();
    }

}

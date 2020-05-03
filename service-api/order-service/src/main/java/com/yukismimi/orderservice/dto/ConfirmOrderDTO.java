package com.yukismimi.orderservice.dto;

import com.yukismimi.orderservice.client.dto.Address;
import com.yukismimi.orderservice.client.dto.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfirmOrderDTO {

    //购物车信息列表
    private List<CartItem> cartItemList;
    //地址列表
    private List<Address> addressList;
    //订单商品总金额
    private BigDecimal totalAmount;
    //运费
    private BigDecimal freightAmount;
    //活动优惠金额
    private BigDecimal promotionAmount;
    //应付金额
    private BigDecimal payAmount;
}

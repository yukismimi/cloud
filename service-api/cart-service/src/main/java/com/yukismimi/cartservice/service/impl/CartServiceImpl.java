package com.yukismimi.cartservice.service.impl;

import com.yukismimi.cartservice.client.ProductClient;
import com.yukismimi.cartservice.client.dto.GetProductDTO;
import com.yukismimi.cartservice.dto.AddItemDTO;
import com.yukismimi.cartservice.dto.EditCheckAllDTO;
import com.yukismimi.cartservice.dto.ModifyCartItemDTO;
import com.yukismimi.cartservice.entity.CartItem;
import com.yukismimi.cartservice.repository.CartRepository;
import com.yukismimi.cartservice.service.CartService;
import com.yukismimi.common.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository repository;

    @Autowired
    ProductClient productClient;

//    @Override
//    public ListCartItemListDTO listItems() {
//
//        Long userId = SecurityUtil.getUserId();
//        List<CartItem> cartItemList = repository.findAllByUserId(userId);
//
//        BigDecimal totalPrice = cartItemList.stream()
//                .map(cartItem -> cartItem.getPrice().multiply(new BigDecimal(cartItem.getQuantity())))
//                .reduce(BigDecimal::add)
//                .orElseThrow();
//
//        int totalNum = cartItemList.stream()
//                .map(CartItem::getQuantity)
//                .reduce(Integer::sum)
//                .orElseThrow();
//
//        return ListCartItemListDTO.builder()
//                .cartItemList(cartItemList)
//                .totalNum(totalNum)
//                .totalPrice(totalPrice)
//                .build();
//    }

    @Override
    public List<CartItem> listItems() {

        Long userId = SecurityUtil.getUserId();
        return repository.findAllByUserId(userId);
    }

    @Override
    public void addItem(AddItemDTO addItemDTO) {
        Long userId = SecurityUtil.getUserId();
        CartItem cartItem = repository.findByUserIdAndProductId(userId, addItemDTO.getId());
        if (cartItem != null) {
            int beforeQuantity = cartItem.getQuantity();
            int afterQuantity = addItemDTO.getQuantity() + beforeQuantity;
            cartItem.setQuantity(afterQuantity);
            repository.save(cartItem);
        } else {
            Timestamp now = Timestamp.valueOf(LocalDateTime.now());
            long productId = addItemDTO.getId();
            GetProductDTO product = productClient.findById(productId).getData();
            CartItem cartItemWillAdd = CartItem.builder()
                    .userId(userId)
                    .productId(productId)
                    .productPic(product.getPic())
                    .price(product.getPrice())
                    .productName(product.getName())
                    .productSkuId(addItemDTO.getProductSkuId())
                    .quantity(addItemDTO.getQuantity())
                    .deleteStatus(0)
                    .createDate(now)
                    .modifyDate(now)
                    .build();
            repository.save(cartItemWillAdd);
        }
    }

    @Override
    public void modifyCartItem(ModifyCartItemDTO modifyCartItemDTO) {
        CartItem cartItem = repository.findById(modifyCartItemDTO.getId()).orElseThrow();
        Optional.ofNullable(modifyCartItemDTO)
                .map(ModifyCartItemDTO::getChecked)
                .ifPresent(cartItem::setChecked);
        Optional.ofNullable(modifyCartItemDTO)
                .map(ModifyCartItemDTO::getQuantity)
                .ifPresent(cartItem::setQuantity);
        repository.save(cartItem);
    }

    @Override
    public void deleteCartItem(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void editCheckAll(EditCheckAllDTO editCheckAllDTO) {
        Long userId = SecurityUtil.getUserId();
        int intChecked = editCheckAllDTO.isChecked() ? 1 : 0;
        List<CartItem> cartItemList = repository.findAllByUserId(userId);
        cartItemList.forEach(cartItem -> cartItem.setChecked(intChecked));
        repository.saveAll(cartItemList);
    }

    @Override
    public void deleteChecked() {
        Long userId = SecurityUtil.getUserId();
        repository.deleteByUserIdAndChecked(userId, 1);
    }
}

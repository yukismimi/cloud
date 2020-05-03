package com.yukismimi.orderservice.dto;

import com.yukismimi.orderservice.client.dto.CartItem;
import lombok.Data;

import java.util.List;

@Data
public class OrderParamDTO {

    private Long userReceiveAddressId;
    private Boolean notFromCart;
    // TODO: 2020/4/29 此处要统一下
    private List<CartItem> cartItemList;

}

package com.yukismimi.cartservice.dto;

import com.yukismimi.cartservice.entity.CartItem;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ListCartItemListDTO {

    private BigDecimal totalPrice;
    private int totalNum;
    private List<CartItem> cartItemList;
}

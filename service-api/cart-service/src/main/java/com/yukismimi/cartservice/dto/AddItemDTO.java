package com.yukismimi.cartservice.dto;

import lombok.Data;

@Data
public class AddItemDTO {
    private long id;
    private long productSkuId;
    private int quantity;
}

package com.yukismimi.cartservice.dto;

import lombok.Data;

@Data
public class ModifyCartItemDTO {
    private Long id;
    private Integer quantity;
    private Integer checked;
}

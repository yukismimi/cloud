package com.yukismimi.orderservice.client.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Stock {

    private Long id;
    private long productId;
    private String skuCode;
    private BigDecimal price;
    private int stock;
    private int lowStock;
    private String sp1;
    private String sp2;
    private String sp3;
    private String pic;
    private int sale;
    private BigDecimal promotionPrice;
    private int lockStock;

}

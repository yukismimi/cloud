package com.yukismimi.orderservice.client.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItem {

    private Long id;
    private long productId;
    private long productSkuId;
    private long userId;
    private int quantity;
    private int checked;
    private BigDecimal price;
    private String sp1;
    private String sp2;
    private String sp3;
    private String productPic;
    private String productName;
    private String productSubTitle;
    private String productSkuCode;
    private String username;
    private java.sql.Timestamp createDate;
    private java.sql.Timestamp modifyDate;
    private int deleteStatus;
    private long productCategoryId;
    private String productBrand;
    private String productSn;
    private String productAttr;
}

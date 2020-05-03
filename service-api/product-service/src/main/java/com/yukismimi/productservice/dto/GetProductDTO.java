package com.yukismimi.productservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetProductDTO {

    private Long id;
    private long brandId;
    private long productCategoryId;
    private long feightTemplateId;
    private long productAttributeCategoryId;
    private String name;
    private String pic;
    private String productSn;
    private int deleteStatus;
    private int publishStatus;
    private int newStatus;
    private int recommandStatus;
    private int verifyStatus;
    private int sort;
    private int sale;
    private BigDecimal price;
    private BigDecimal promotionPrice;
    private int giftGrowth;
    private int giftPoint;
    private int usePointLimit;
    private String subTitle;
    private String description;
    private BigDecimal originalPrice;
    private int stock;
    private int lowStock;
    private String unit;
    private BigDecimal weight;
    private int previewStatus;
    private String serviceIds;
    private String keywords;
    private String note;
    private String albumPics;
    private String detailTitle;
    private String detailDesc;
    private String detailHtml;
    private String detailMobileHtml;
    private int promotionPerLimit;
    private int promotionType;
    private String brandName;
    private String productCategoryName;

}

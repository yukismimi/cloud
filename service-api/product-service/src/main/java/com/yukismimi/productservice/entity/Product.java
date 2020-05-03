package com.yukismimi.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private java.sql.Timestamp promotionStartTime;
    private java.sql.Timestamp promotionEndTime;
    private int promotionPerLimit;
    private int promotionType;
    private String brandName;
    private String productCategoryName;

}

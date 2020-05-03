package com.yukismimi.orderservice.entity;

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
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long orderId;
    private String orderSn;
    private long productId;
    private String productPic;
    private String productName;
    private String productBrand;
    private String productSn;
    private BigDecimal productPrice;
    private int productQuantity;
    private long productSkuId;
    private String productSkuCode;
    private long productCategoryId;
    private String sp1;
    private String sp2;
    private String sp3;
    private String promotionName;
    private BigDecimal promotionAmount;
    private BigDecimal couponAmount;
    private BigDecimal integrationAmount;
    private BigDecimal realAmount;
    private int giftIntegration;
    private int giftGrowth;
    private String productAttr;

}

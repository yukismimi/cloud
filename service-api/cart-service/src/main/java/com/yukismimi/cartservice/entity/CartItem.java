package com.yukismimi.cartservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

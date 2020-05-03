package com.yukismimi.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long parentId;
    private String name;
    private int level;
    private int productCount;
    private String productUnit;
    private int navStatus;
    private int showStatus;
    private int sort;
    private String icon;
    private String keywords;
    private String description;

}

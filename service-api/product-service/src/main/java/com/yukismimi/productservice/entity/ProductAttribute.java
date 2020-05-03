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
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long productAttributeCategoryId;
    private String name;
    private int selectType;
    private int inputType;
    private String inputList;
    private int sort;
    private int filterType;
    private int searchType;
    private int relatedStatus;
    private int handAddStatus;
    private int type;

}

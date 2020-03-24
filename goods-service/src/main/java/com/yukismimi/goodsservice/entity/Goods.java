package com.yukismimi.goodsservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer goodsId;
    private String name;
    private BigDecimal price;
    private int stock;
    private String note;
    private int sellStatus;
    private int deleteStatus;
}

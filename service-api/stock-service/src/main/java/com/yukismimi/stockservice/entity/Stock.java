package com.yukismimi.stockservice.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "sku_stock")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

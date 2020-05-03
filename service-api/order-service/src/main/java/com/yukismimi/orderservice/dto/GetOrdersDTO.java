package com.yukismimi.orderservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GetOrdersDTO {

    private Long id;
    private BigDecimal totalAmount;
    private int status;
    private java.sql.Timestamp paymentTime;
    private List<OrderItemDTO> orderItemList;

    public static class OrderItemDTO {
        private Long id;
        private String productPic;
        private String productName;
        private int productQuantity;
        private BigDecimal realAmount;
    }
}

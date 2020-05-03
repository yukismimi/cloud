package com.yukismimi.orderservice.dto;

import com.yukismimi.orderservice.entity.Order;
import com.yukismimi.orderservice.entity.OrderItem;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResultDTO {

    private Order order;
    private List<OrderItem> orderItemList;
}

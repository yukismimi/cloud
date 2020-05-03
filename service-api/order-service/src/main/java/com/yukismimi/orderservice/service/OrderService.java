package com.yukismimi.orderservice.service;

import com.yukismimi.orderservice.dto.ConfirmOrderDTO;
import com.yukismimi.orderservice.dto.OrderParamDTO;
import com.yukismimi.orderservice.dto.OrderResultDTO;
import com.yukismimi.orderservice.entity.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface OrderService {

    @Transactional
    OrderResultDTO generateOrder(OrderParamDTO orderParamDTO);

    ConfirmOrderDTO generateConfirmOrder();

    //    @Transactional
    void cancelOrder(Long orderId);

    @Transactional
    void pay(String orderNo);

    List<Order> getAllOrder();

    Order getOrder(Long orderId);
}

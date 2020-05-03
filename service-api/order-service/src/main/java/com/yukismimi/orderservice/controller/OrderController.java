package com.yukismimi.orderservice.controller;

import com.yukismimi.common.api.CommonResult;
import com.yukismimi.orderservice.dto.ConfirmOrderDTO;
import com.yukismimi.orderservice.dto.OrderParamDTO;
import com.yukismimi.orderservice.dto.OrderResultDTO;
import com.yukismimi.orderservice.entity.Order;
import com.yukismimi.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService service;

    @GetMapping("/all")
    public CommonResult<List<Order>> getAllOrder() {
        return CommonResult.success(service.getAllOrder());
    }

    @PostMapping("/add")
    public CommonResult<OrderResultDTO> generateOrder(@RequestBody OrderParamDTO orderParamDTO) {
        return CommonResult.success(service.generateOrder(orderParamDTO));
    }

    @GetMapping("/confirm")
    public CommonResult<ConfirmOrderDTO> generateConfirmOrder() {
        return CommonResult.success(service.generateConfirmOrder());
    }

    @GetMapping("/get")
    public CommonResult<Order> getOrder(@RequestParam("orderId") Long orderId) {
        return CommonResult.success(service.getOrder(orderId));
    }
}

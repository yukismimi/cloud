package com.yukismimi.orderservice.component;

import com.yukismimi.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "mall.order.cancel")
@Slf4j
public class OrderCancelReceiver {

    @Autowired
    private OrderService service;

    @RabbitHandler
    public void handle(Long orderId) {
        log.info("receive orderId:{}", orderId);
        service.cancelOrder(orderId);
    }
}

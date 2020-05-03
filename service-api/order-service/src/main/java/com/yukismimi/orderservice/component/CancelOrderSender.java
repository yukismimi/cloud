package com.yukismimi.orderservice.component;

import com.yukismimi.common.utils.SecurityUtil;
import com.yukismimi.orderservice.domain.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CancelOrderSender {

    @Autowired
    private RabbitTemplate template;

    public void sendMessage(Long orderId, int delay) {
        template.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(),
                QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(),
                orderId,
                message -> {
                    log.info("delay : {}", delay);
                    //给消息设置延迟毫秒值
                    message.getMessageProperties().setExpiration(String.valueOf(delay));
                    message.getMessageProperties().setHeader("id", SecurityUtil.getUserId().toString());
                    message.getMessageProperties().setHeader("username", SecurityUtil.getUsername());
//                    message.getMessageProperties().setDelay(delay);
                    return message;
                });
    }
}

package com.order.order_api.messaging.producer.createorder;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.order.order_api.dtos.OrderModelDto;

@Component
public class OrderModelCreatedProducer{
    
    private final RabbitTemplate rabbitTemplate;

    public OrderModelCreatedProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void orderCreated(OrderModelDto orderModelDto){
        rabbitTemplate.convertAndSend(
            "order.exchange",
            "order.created",
            orderModelDto
        );
    }
}

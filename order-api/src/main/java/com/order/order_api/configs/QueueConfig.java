package com.order.order_api.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Bean
    public Queue customerCheckQueue() {
        return new Queue("customer.check.queue", true);
    }

    @Bean
    public Queue productCheckQueue() {
        return new Queue("inventory.check.queue", true);
    }

    @Bean
    public Queue orderCreatedQueue() {
        return new Queue("order.created.queue", true);
    }

    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange("order.exchange");
    }

}

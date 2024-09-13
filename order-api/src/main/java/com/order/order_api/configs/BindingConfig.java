package com.order.order_api.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BindingConfig {
    
    @Bean
    public Binding bindingCustomerCheckQueue(Queue customerCheckQueue, TopicExchange orderExchange) {
        return BindingBuilder.bind(customerCheckQueue).to(orderExchange).with("check.customer");
    }

    @Bean
    public Binding bindingProductCheckQueue(Queue productCheckQueue, TopicExchange orderExchange) {
        return BindingBuilder.bind(productCheckQueue).to(orderExchange).with("check.inventory");
    }

    @Bean
    public Binding bindingOrderCreatedQueue(Queue orderCreatedQueue, TopicExchange orderExchange) {
        return BindingBuilder.bind(orderCreatedQueue).to(orderExchange).with("order.created");
    }
}

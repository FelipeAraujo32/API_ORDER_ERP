package com.order.order_api.messaging.producer.createorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.order.order_api.dtos.OrderModelDto;
import com.order.order_api.globalexception.NotFoundException;
import com.order.order_api.globalexception.ServiceUnavailableException;

@Component
public class CustomerRequestProducer {

    private final RabbitTemplate rabbitTemplate;

    public CustomerRequestProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private static final Logger logger = LoggerFactory.getLogger(CustomerRequestProducer.class);

    public void checkCustomerExists(OrderModelDto orderModelDto) {

        try {
            Boolean customerExists = (Boolean) rabbitTemplate.convertSendAndReceive(
                    "order.exchange",
                    "check.customer",
                    orderModelDto.getCustomerId());
            validateCustomerExists(customerExists);
        } catch (ServiceUnavailableException | NotFoundException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error(
                    "Unexpected error while checking product with UUID: {}",
                    orderModelDto.getProductId(), e);
            throw new RuntimeException(
                    "Unexpected error occurred. Please try again later.", e);
        }
    }

    private void validateCustomerExists(Boolean customerExists) {
        if (customerExists == null) {
            throw new ServiceUnavailableException(
                    "No response from customer service. Please try again later.");
        } else if (Boolean.FALSE.equals(customerExists)) {
            throw new NotFoundException("Product not found.");
        }
    }
}

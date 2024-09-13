package com.order.order_api.messaging.producer.createorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.order.order_api.convert.InventoryConvert;
import com.order.order_api.dtos.InventoryCheckDto;
import com.order.order_api.dtos.OrderModelDto;
import com.order.order_api.globalexception.NotFoundException;
import com.order.order_api.globalexception.ServiceUnavailableException;

@Component
public class InventoryRequestProducer {

    private final RabbitTemplate rabbitTemplate;

    private static final Logger logger = LoggerFactory.getLogger(CustomerRequestProducer.class);

    public InventoryRequestProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendInventoryCheck(OrderModelDto orderModelDto) {
        InventoryCheckDto inventoryCheckDto = new InventoryConvert()
                .inventoryConvert(orderModelDto);

        try {
            Boolean inventoryCheck = (Boolean) rabbitTemplate.convertSendAndReceive(
                    "order.exchange",
                    "check.inventory",
                    inventoryCheckDto);

            validateInventoryCheck(inventoryCheck);
        } catch (Exception e) {
            logger.error(
                    "Unexpected error while checking inventory with UUID: {}",
                    inventoryCheckDto.getProductId(), e);
            throw new RuntimeException(
                    "Unexpected error occurred. Please try again later.", e);
        }
    }

    private void validateInventoryCheck(Boolean inventoryExists) {
        if (inventoryExists == null) {
            throw new ServiceUnavailableException(
                    "No response from Inventory service. Please try again later.");
        } else if (Boolean.FALSE.equals(inventoryExists)) {
            throw new NotFoundException("Inventory not found.");
        }
    }
}

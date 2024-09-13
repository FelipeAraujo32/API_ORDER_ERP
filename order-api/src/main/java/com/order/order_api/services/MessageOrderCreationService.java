package com.order.order_api.services;

import org.springframework.amqp.AmqpException;
import org.springframework.stereotype.Service;

import java.io.IOException;

import com.order.order_api.dtos.OrderModelDto;
import com.order.order_api.messaging.producer.createorder.CustomerRequestProducer;
import com.order.order_api.messaging.producer.createorder.InventoryRequestProducer;
import com.order.order_api.messaging.producer.createorder.OrderModelCreatedProducer;

@Service
public class MessageOrderCreationService {
    
    private final CustomerRequestProducer customerRequestProducer;
    private final InventoryRequestProducer inventoryRequestProducer;
    private final OrderModelCreatedProducer orderModelCreatedProducer;
    
    public MessageOrderCreationService(CustomerRequestProducer customerRequestProducer,
            InventoryRequestProducer inventoryRequestProducer, OrderModelCreatedProducer orderModelCreatedProducer) {
        this.customerRequestProducer = customerRequestProducer;
        this.inventoryRequestProducer = inventoryRequestProducer;
        this.orderModelCreatedProducer = orderModelCreatedProducer;
    }

    public void checkInventoryAndCustomer(OrderModelDto orderModelDto) throws AmqpException, IOException, ClassNotFoundException {
        inventoryRequestProducer.sendInventoryCheck(orderModelDto);
        customerRequestProducer.checkCustomerExists(orderModelDto);
    }

    public void sendOrderCreated(OrderModelDto orderModelDto){
        orderModelCreatedProducer.orderCreated(orderModelDto);
    }
    
}

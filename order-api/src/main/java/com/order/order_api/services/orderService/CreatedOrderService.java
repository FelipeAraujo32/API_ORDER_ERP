package com.order.order_api.services.orderService;

import java.io.IOException;

import org.springframework.amqp.AmqpException;
import org.springframework.stereotype.Service;

import com.order.order_api.convert.OrderModelConvert;
import com.order.order_api.dtos.OrderModelDto;
import com.order.order_api.models.OrderModel;
import com.order.order_api.repository.OrderRepository;
import com.order.order_api.services.MessageOrderCreationService;

@Service
public class CreatedOrderService {
    
    private final OrderRepository orderRepository;
    private final OrderModelConvert orderModelConvert;
    private final MessageOrderCreationService messageOrderCreationService;
    
    public CreatedOrderService(OrderRepository orderRepository, OrderModelConvert orderModelConvert,
            MessageOrderCreationService messageOrderCreationService) {
        this.orderRepository = orderRepository;
        this.orderModelConvert = orderModelConvert;
        this.messageOrderCreationService = messageOrderCreationService;
    }

    public OrderModelDto createOrder(OrderModelDto orderModeldDto)
            throws AmqpException, ClassNotFoundException, IOException {

        messageOrderCreationService.checkInventoryAndCustomer(orderModeldDto);

        OrderModel toOrderModel = orderModelConvert.toOrderModel(orderModeldDto);
        OrderModel createdOrderSaved = orderRepository.save(toOrderModel);
        OrderModelDto toOrderModelDto = orderModelConvert.toOrderDto(createdOrderSaved);

        messageOrderCreationService.sendOrderCreated(toOrderModelDto);
        return toOrderModelDto;
    } 

    
}

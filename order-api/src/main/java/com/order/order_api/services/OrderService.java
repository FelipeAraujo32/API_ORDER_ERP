package com.order.order_api.services;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.amqp.AmqpException;
import org.springframework.stereotype.Service;

import com.order.order_api.convert.OrderModelConvert;
import com.order.order_api.dtos.OrderModelDto;
import com.order.order_api.models.OrderModel;
import com.order.order_api.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderModelConvert orderModelConvert;
    private final MessageOrderCreationService messageOrderCreationService;

    public OrderService(OrderRepository orderRepository, OrderModelConvert orderModelConvert,
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

    private OrderModel findByOrderModelId(UUID orderModelId) {
        Optional<OrderModel> optionalOrderModel = orderRepository.findById(orderModelId);
        return optionalOrderModel.get();
    }

    public void setStatusOrder(String setStatusOrder, UUID orderId) {
        var orderModel = findByOrderModelId(orderId);
        orderModel.setStatus(setStatusOrder);
    }

    public OrderModel orderById(UUID uuid) {
        return orderRepository.findById(uuid).orElseThrow(NoSuchElementException::new);
    }

    public OrderModel updateOrder(UUID uuid, OrderModel orderModel) {
        OrderModel orderDb = this.orderById(uuid);
        if (!orderDb.getOrderId().equals(uuid)) {
            // Implt Exception
        }
        // Implt Validador de Dados, classe externa
        orderModel.setOrderId(uuid);
        return orderRepository.save(orderModel);
    }

    public void deleteOrder(UUID uuid) {
        OrderModel orderDb = this.orderById(uuid);
        this.orderRepository.delete(orderDb);
    }
}

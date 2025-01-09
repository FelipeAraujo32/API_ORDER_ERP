package com.order.order_api.services.orderService;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.order.order_api.enumOrder.OrderStatus;
import com.order.order_api.models.OrderModel;
import com.order.order_api.repository.OrderRepository;

@Service
public class StatusOrderService {
    
    private final OrderRepository orderRepository;
    private final FindOrderService findOrderService;

    public StatusOrderService(OrderRepository orderRepository, FindOrderService findOrderService) {
        this.orderRepository = orderRepository;
        this.findOrderService = findOrderService;
    }

    public void updateOrderStatus(String status, UUID orderId) {
        OrderModel orderModel = findOrderService.findByOrderUUID(orderId);
        OrderStatus.validate(status);;
        orderModel.setStatus(status);
        orderRepository.save(orderModel);
    }
}

package com.order.order_api.services.orderService;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.order.order_api.globalexception.OrderNotFoundException;
import com.order.order_api.models.OrderModel;
import com.order.order_api.repository.OrderRepository;

@Service
public class FindOrderService {
    
    private final OrderRepository orderRepository;

    public FindOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderModel findByOrderUUID(UUID orderModelId) {
        OrderModel orderModel = orderRepository.findById(orderModelId)
        .orElseThrow(() -> new OrderNotFoundException("Order with ID " + orderModelId + " not found."));
        return orderModel;
    }
}

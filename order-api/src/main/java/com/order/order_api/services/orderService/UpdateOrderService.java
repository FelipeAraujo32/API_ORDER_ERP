package com.order.order_api.services.orderService;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.order.order_api.models.OrderModel;
import com.order.order_api.repository.OrderRepository;

@Service
public class UpdateOrderService {
    
    private final OrderRepository orderRepository;
    private final FindOrderService findOrderService;

    public UpdateOrderService(OrderRepository orderRepository, FindOrderService findOrderService) {
        this.orderRepository = orderRepository;
        this.findOrderService = findOrderService;
    }

    public OrderModel updateOrder(UUID uuid, OrderModel orderModel) {
        OrderModel orderDb = findOrderService.findByOrderUUID(uuid);
        if (!orderDb.getOrderId().equals(uuid)) {
            // Implt Exception
        }
        // Implt Validador de Dados, classe externa
        orderModel.setOrderId(uuid);
        return orderRepository.save(orderModel);
    }

    
}

package com.order.order_api.convert;

import org.springframework.stereotype.Component;

import com.order.order_api.dtos.OrderModelDto;
import com.order.order_api.models.OrderModel;

@Component
public class OrderModelConvert {
    
    public OrderModelDto toOrderDto(OrderModel orderModel){
        OrderModelDto orderDto = new OrderModelDto();
        orderDto.setOrderId(orderModel.getOrderId());
        orderDto.setCustomerId(orderModel.getCustomerId());
        orderDto.setProductId(orderModel.getProductId());
        orderDto.setOrderData(orderModel.getOrderData());
        orderDto.setStockQuantity(orderModel.getStockQuantity());
        orderDto.setStatus(orderModel.getStatus());
        return orderDto;
    }

    public OrderModel toOrderModel(OrderModelDto orderDto){
        OrderModel orderModel = new OrderModel();
        orderModel.setCustomerId(orderDto.getCustomerId());
        orderModel.setProductId(orderDto.getProductId());
        orderModel.setStockQuantity(orderDto.getStockQuantity());
        orderModel.setStatus(orderDto.getStatus());
        orderModel.setOrderData(orderDto.getOrderData());
        return orderModel;
    }
}

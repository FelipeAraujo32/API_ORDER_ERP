package com.order.order_api.dtos;

import java.time.LocalDate;
import java.util.UUID;

public class OrderModelDto {
    
    private UUID orderId;
    private UUID customerId;
    private UUID productId;
    private double stockQuantity;
    private LocalDate orderData;
    private String status;


    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public LocalDate getOrderData() {
        return orderData;
    }

    public void setOrderData(LocalDate orderData) {
        this.orderData = orderData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(double stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }
}

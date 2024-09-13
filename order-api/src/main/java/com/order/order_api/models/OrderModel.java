package com.order.order_api.models;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "order")
@Table(name = "order_erp")
public class OrderModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId;
    private UUID customerId;
    private UUID productId;
    private double stockQuantity;
    private LocalDate orderData;
    private String status;
    
    public OrderModel() {
    }

    public OrderModel(UUID customerId, UUID productId, double stockQuantity, LocalDate orderData, String status) {
        this.customerId = customerId;
        this.productId = productId;
        this.stockQuantity = stockQuantity;
        this.orderData = orderData;
        this.status = status;
    }


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

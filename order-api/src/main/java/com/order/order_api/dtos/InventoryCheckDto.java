package com.order.order_api.dtos;

import java.util.UUID;

public class InventoryCheckDto {
    private UUID productId;
    private double stockQuantity;
    
    public UUID getProductId() {
        return productId;
    }
    public void setProductId(UUID productId) {
        this.productId = productId;
    }
    
    public double getStockQuantity() {
        return stockQuantity;
    }
    public void setStockQuantity(double stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    

    
}

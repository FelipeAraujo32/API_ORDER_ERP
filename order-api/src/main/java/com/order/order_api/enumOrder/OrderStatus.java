package com.order.order_api.enumOrder;


public enum OrderStatus {
    PENDING,
    APPROVED,
    CANCELLED;

    public static void validate(String status) {
        try {
            OrderStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
    }
}

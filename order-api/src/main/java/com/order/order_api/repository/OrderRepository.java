package com.order.order_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.order_api.models.OrderModel;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, UUID>{

}

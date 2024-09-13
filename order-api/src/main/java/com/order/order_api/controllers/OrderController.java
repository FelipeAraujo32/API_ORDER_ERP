package com.order.order_api.controllers;

import java.io.IOException;
import java.net.URI;
import java.util.UUID;

import org.springframework.amqp.AmqpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.order.order_api.convert.OrderModelConvert;
import com.order.order_api.dtos.OrderModelDto;
import com.order.order_api.services.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<OrderModelDto> findByOrder(@PathVariable UUID uuid) {
        var findByOrder = service.orderById(uuid);
        return ResponseEntity.ok(new OrderModelConvert().toOrderDto(findByOrder));
    }

    @PostMapping()
    public ResponseEntity<OrderModelDto> createOrder(@Valid @RequestBody OrderModelDto orderDTO) throws AmqpException, ClassNotFoundException, IOException {
        var createOrder = service.createOrder(orderDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(createOrder.getOrderId())
                .toUri();

        return ResponseEntity.created(location).body((createOrder));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<OrderModelDto> updateOrder(@PathVariable UUID uuid,
            @Valid @RequestBody OrderModelDto orderDto) {
        var updateOrder = service.updateOrder(uuid, new OrderModelConvert().toOrderModel(orderDto));

        return ResponseEntity.status(HttpStatus.OK).body(new OrderModelConvert().toOrderDto(updateOrder));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID uuid) {
        service.deleteOrder(uuid);
        return ResponseEntity.noContent().build();
    }

}

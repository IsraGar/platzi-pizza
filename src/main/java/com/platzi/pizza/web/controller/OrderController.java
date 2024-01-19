package com.platzi.pizza.web.controller;


import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("")
    public ResponseEntity<List<OrderEntity>> getAll(){
        return ResponseEntity.ok(this.orderService.getAll());
    }

    @GetMapping("/after")
    public ResponseEntity<List<OrderEntity>> getOrdersAfter(){
        return ResponseEntity.ok(this.orderService.getOrdersAfter());
    }

    @GetMapping("/before")
    public ResponseEntity<List<OrderEntity>> getOrdersBefore(){
        return ResponseEntity.ok(this.orderService.getOrdersBefore());
    }

    @GetMapping("/out")
    public ResponseEntity<List<OrderEntity>> getOutsideOrders(){
        return ResponseEntity.ok(this.orderService.getOutsideOrders());
    }
}
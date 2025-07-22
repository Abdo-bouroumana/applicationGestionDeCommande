package com.abderrazak.applicationGestion.controller;


import com.abderrazak.applicationGestion.model.Order;
import com.abderrazak.applicationGestion.model.OrderStatus;
import com.abderrazak.applicationGestion.model.OrderType;
import com.abderrazak.applicationGestion.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> gelAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Optional<Order>> createOrder(@RequestBody Order order){
        Optional<Order> newOrder = orderService.createOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id){
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateOrder(@RequestBody Order order){
        boolean isUpdated = orderService.updateOrder(order);
        if (isUpdated) {
            return new ResponseEntity<>("Order updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update order", HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id){
        orderService.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<List<Order>> getOrderByUsername(@PathVariable("username") String username){
        List<Order> orders = orderService.getOrdersByUsername(username);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/findByStatus/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable("status") OrderStatus status){
        List<Order> orders = orderService.getOrdersByStatus(status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/findByType/{type}")
    public ResponseEntity<List<Order>> getOrdersByType(@PathVariable("type") OrderType type){
        List<Order> orders = orderService.getOrdersByType(type);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}

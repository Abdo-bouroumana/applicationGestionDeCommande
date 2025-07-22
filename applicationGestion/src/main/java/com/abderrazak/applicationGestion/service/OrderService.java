package com.abderrazak.applicationGestion.service;

import com.abderrazak.applicationGestion.model.Order;
import com.abderrazak.applicationGestion.model.OrderStatus;
import com.abderrazak.applicationGestion.model.OrderType;
import com.abderrazak.applicationGestion.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    public Optional<Order> createOrder(Order order){
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAllByIsDeletedFalse();
    }

    public boolean updateOrder(Order order) {
        return orderRepository.update(order);
    }

    public void deleteOrderById(Long id) {
        orderRepository.softDeleteById(id);
    }

    public List<Order> getOrdersByUsername(String username){
        Long userId = userService.getUserByUsername(username).getId();
        return orderRepository.findByUserIdAndIsDeletedFalse(userId);
    }

    public List<Order> getOrdersByStatus(OrderStatus status){
        return orderRepository.findByStatusAndIsDeletedFalse(status);
    }

    public List<Order> getOrdersByType(OrderType orderType){
        return orderRepository.findByTypeAndIsDeletedFalse(orderType);
    }

    public List<Order> getOrdersByCreatedAtBetween(LocalDateTime start, LocalDateTime end) {
        return orderRepository.findByCreatedAtBetweenAndIsDeletedFalse(start, end);
    }

}

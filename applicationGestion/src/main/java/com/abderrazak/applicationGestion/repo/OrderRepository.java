package com.abderrazak.applicationGestion.repo;

import com.abderrazak.applicationGestion.model.OrderStatus;
import com.abderrazak.applicationGestion.model.Order;
import com.abderrazak.applicationGestion.model.OrderType;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface OrderRepository {
    Optional<Order> save(Order order);
    Optional<Order> findById(Long id);
    List<Order> findByUserIdAndIsDeletedFalse(Long userId);
    List<Order> findAllByIsDeletedFalse();
    List<Order> findByStatusAndIsDeletedFalse(OrderStatus status);
    List<Order> findByTypeAndIsDeletedFalse(OrderType type);
    List<Order> findByUserIdAndStatusAndIsDeletedFalse(Long userId, OrderStatus status, int offset, int limit);
    List<Order> findByCreatedAtBetweenAndIsDeletedFalse(LocalDateTime start, LocalDateTime end);
    boolean update(Order order);
    boolean softDeleteById(Long id);
    long countByStatus(OrderStatus status);
    long countByIsDeletedFalse();
    long countByUserIdAndIsDeletedFalse(Long userId);
}

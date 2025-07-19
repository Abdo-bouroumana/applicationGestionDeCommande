package com.abderrazak.applicationGestion.repo;

import com.abderrazak.applicationGestion.model.OrderStatus;
import com.abderrazak.applicationGestion.model.Orders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Orders save(Orders order);
    Optional<Orders> findById(Long id);
    List<Orders> findByUserIdAndIsDeletedFalse(Long userId, int offset, int limit);
    List<Orders> findAllByIsDeletedFalse(int offset, int limit);
    List<Orders> findByStatusAndIsDeletedFalse(OrderStatus status, int offset, int limit);
    List<Orders> findByUserIdAndStatusAndIsDeletedFalse(Long userId, OrderStatus status, int offset, int limit);
    List<Orders> findByCreatedAtBetweenAndIsDeletedFalse(LocalDateTime start, LocalDateTime end, int offset, int limit);
    boolean update(Orders order);
    boolean softDeleteById(Long id);
    long countByStatus(OrderStatus status);
    long countByIsDeletedFalse();
    long countByUserIdAndIsDeletedFalse(Long userId);
}

package com.abderrazak.applicationGestion.repo;

import com.abderrazak.applicationGestion.model.OrderStatus;
import com.abderrazak.applicationGestion.model.Orders;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {
    private final JdbcTemplate jdbcTemplate;
    private final OrderRowMapper orderRowMapper;

    public OrderRepositoryImpl(JdbcTemplate jdbcTemplate, OrderRowMapper orderRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.orderRowMapper = orderRowMapper;
    }

    @Override
    public Orders save(Orders order) {
        String sql = """
            INSERT INTO orders (user_id, title, type, quantity, status, comment, is_deleted)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;
        jdbcTemplate.update(sql,
                order.getUserId(),
                order.getTitle(),
                order.getType().name(),
                order.getQuantity(),
                order.getStatus().name(),
                order.getComment(),
                order.isDeleted()
        );
        return order;
    }

    @Override
    public Optional<Orders> findById(Long id) {
        try {
            String sql = "SELECT * FROM orders WHERE id = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, orderRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Orders> findByUserIdAndIsDeletedFalse(Long userId, int offset, int limit) {
        String sql = "SELECT * FROM orders WHERE user_id = ? AND is_deleted = false LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, orderRowMapper, userId, limit, offset);
    }

    @Override
    public List<Orders> findAllByIsDeletedFalse(int offset, int limit) {
        String sql = "SELECT * FROM orders WHERE is_deleted = false LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, orderRowMapper, limit, offset);
    }

    @Override
    public List<Orders> findByStatusAndIsDeletedFalse(OrderStatus status, int offset, int limit) {
        String sql = "SELECT * FROM orders WHERE status = ? AND is_deleted = false LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, orderRowMapper, status.name(), limit, offset);
    }

    @Override
    public List<Orders> findByUserIdAndStatusAndIsDeletedFalse(Long userId, OrderStatus status, int offset, int limit) {
        String sql = "SELECT * FROM orders WHERE user_id = ? AND status = ? AND is_deleted = false LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, orderRowMapper, userId, status.name(), limit, offset);
    }

    @Override
    public List<Orders> findByCreatedAtBetweenAndIsDeletedFalse(LocalDateTime start, LocalDateTime end, int offset, int limit) {
        String sql = "SELECT * FROM orders WHERE created_at BETWEEN ? AND ? AND is_deleted = false LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, orderRowMapper, start, end, limit, offset);
    }

    @Override
    public boolean update(Orders order) {
        String sql = """
            UPDATE orders SET title = ?, type = ?, quantity = ?, status = ?, comment = ?, 
            updated_at = ?, is_deleted = ? WHERE id = ?
        """;
        int rows = jdbcTemplate.update(sql,
                order.getTitle(),
                order.getType().name(),
                order.getQuantity(),
                order.getStatus().name(),
                order.getComment(),
                order.getUpdatedAt(),
                order.isDeleted(),
                order.getId()
        );
        return rows > 0;
    }

    @Override
    public boolean softDeleteById(Long id) {
        String sql = "UPDATE orders SET is_deleted = true WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    public long countByStatus(OrderStatus status) {
        String sql = "SELECT COUNT(*) FROM orders WHERE status = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, status.name());
    }

    @Override
    public long countByIsDeletedFalse() {
        String sql = "SELECT COUNT(*) FROM orders WHERE is_deleted = false";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public long countByUserIdAndIsDeletedFalse(Long userId) {
        String sql = "SELECT COUNT(*) FROM orders WHERE user_id = ? AND is_deleted = false";
        return jdbcTemplate.queryForObject(sql, Long.class, userId);
    }
}

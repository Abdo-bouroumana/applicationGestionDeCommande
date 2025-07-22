package com.abderrazak.applicationGestion.repo;

import com.abderrazak.applicationGestion.model.OrderStatus;
import com.abderrazak.applicationGestion.model.Order;
import com.abderrazak.applicationGestion.model.OrderType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final JdbcTemplate jdbcTemplate;
    private final OrderRowMapper orderRowMapper;

    public OrderRepositoryImpl(JdbcTemplate jdbcTemplate, OrderRowMapper orderRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.orderRowMapper = orderRowMapper;
    }

    @Override
    public Optional<Order> save(Order order) {
        String sql = """
            INSERT INTO orders (user_id, title, type, quantity, comment)
            VALUES (?, ?, ?, ?, ?)
        """;
        jdbcTemplate.update(sql,
                order.getUser_id(),
                order.getTitle(),
                order.getType().name(),
                order.getQuantity(),
                order.getComment()
        );
        return findById(order.getId());
    }

    @Override
    public Optional<Order> findById(Long id) {
        try {
            String sql = "SELECT * FROM orders WHERE id = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, orderRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Order> findByUserIdAndIsDeletedFalse(Long userId) {
        String sql = "SELECT * FROM orders WHERE user_id = ? AND is_deleted = false";
        return jdbcTemplate.query(sql, orderRowMapper, userId);
    }

    @Override
    public List<Order> findAllByIsDeletedFalse() {
        String sql = "SELECT * FROM orders WHERE is_deleted = false ";
        return jdbcTemplate.query(sql, orderRowMapper);
    }

    @Override
    public List<Order> findByStatusAndIsDeletedFalse(OrderStatus status) {
        String sql = "SELECT * FROM orders WHERE status = ? AND is_deleted = false ";
        return jdbcTemplate.query(sql, orderRowMapper, status.name());
    }

    public List<Order> findByTypeAndIsDeletedFalse(OrderType type){
        String sql = "SELECT * FROM orders WHERE type = ? AND is_deleted = false";
        return jdbcTemplate.query(sql, orderRowMapper, type.name());
    }

    @Override
    public List<Order> findByUserIdAndStatusAndIsDeletedFalse(Long userId, OrderStatus status, int offset, int limit) {
        String sql = "SELECT * FROM orders WHERE user_id = ? AND status = ? AND is_deleted = false LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, orderRowMapper, userId, status.name(), limit, offset);
    }

    @Override
    public List<Order> findByCreatedAtBetweenAndIsDeletedFalse(LocalDateTime start, LocalDateTime end) {
        String sql = "SELECT * FROM orders WHERE created_at BETWEEN ? AND ? AND is_deleted = false";
        return jdbcTemplate.query(sql, orderRowMapper, start, end);
    }

    @Override
    public boolean update(Order order) {
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
                LocalDateTime.now(),
                order.isIs_deleted(),
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

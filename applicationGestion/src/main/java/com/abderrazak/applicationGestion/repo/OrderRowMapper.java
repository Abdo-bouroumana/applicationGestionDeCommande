package com.abderrazak.applicationGestion.repo;

import com.abderrazak.applicationGestion.model.OrderStatus;
import com.abderrazak.applicationGestion.model.OrderType;
import com.abderrazak.applicationGestion.model.Order;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setUser_id(rs.getLong("user_id"));
        order.setTitle(rs.getString("title"));
        order.setType(OrderType.valueOf(rs.getString("type")));
        order.setQuantity(rs.getInt("quantity"));
        order.setStatus(OrderStatus.valueOf(rs.getString("status")));
        order.setComment(rs.getString("comment"));
        order.setCreated_at(rs.getObject("created_at", LocalDateTime.class));
        order.setUpdated_at(rs.getObject("updated_at", LocalDateTime.class));
        order.setIs_Deleted(rs.getBoolean("is_deleted"));
        return order;
    }
}

package com.abderrazak.applicationGestion.repo;

import com.abderrazak.applicationGestion.model.OrderStatus;
import com.abderrazak.applicationGestion.model.OrderType;
import com.abderrazak.applicationGestion.model.Orders;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OrderRowMapper implements RowMapper<Orders> {
    @Override
    public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
        Orders order = new Orders();
        order.setId(rs.getLong("id"));
        order.setUserId(rs.getLong("user_id"));
        order.setTitle(rs.getString("title"));
        order.setType(OrderType.valueOf(rs.getString("type")));
        order.setQuantity(rs.getInt("quantity"));
        order.setStatus(OrderStatus.valueOf(rs.getString("status")));
        order.setComment(rs.getString("comment"));
        order.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
        order.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
        order.setIs_Deleted(rs.getBoolean("is_deleted"));
        return order;
    }
}

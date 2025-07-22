package com.abderrazak.applicationGestion.repo;

import com.abderrazak.applicationGestion.model.Role;
import com.abderrazak.applicationGestion.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRole(Role.valueOf(rs.getString("role")));
        user.setIs_active(rs.getBoolean("is_active"));
        user.setFirst_login(rs.getBoolean("first_login"));
        user.setCreated_at(rs.getObject("created_at", LocalDateTime.class));
        return user;
    }
}

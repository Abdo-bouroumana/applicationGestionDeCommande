package com.abderrazak.applicationGestion.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.abderrazak.applicationGestion.model.AuditAction;
import com.abderrazak.applicationGestion.model.AuditLog;
import org.springframework.jdbc.core.RowMapper;

public class AuditLogRowMapper implements RowMapper<AuditLog> {
    @Override
    public AuditLog mapRow(ResultSet rs, int rowNum) throws SQLException {
        AuditLog audit = new AuditLog();
        audit.setId(rs.getLong("id"));
        audit.setUserId(rs.getLong("user_id"));
        audit.setTableName(rs.getString("table_name"));
        audit.setAction(AuditAction.valueOf(rs.getString("action")));
        audit.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
        return audit;
    }
}

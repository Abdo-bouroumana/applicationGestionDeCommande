package com.abderrazak.applicationGestion.repo;

import com.abderrazak.applicationGestion.model.AuditAction;
import com.abderrazak.applicationGestion.model.AuditLog;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class AuditLogRepositoryImpl implements AuditLogRepository {
    private final JdbcTemplate jdbcTemplate;
    private final AuditLogRowMapper auditLogRowMapper;

    public AuditLogRepositoryImpl(JdbcTemplate jdbcTemplate, AuditLogRowMapper auditLogRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.auditLogRowMapper = auditLogRowMapper;
    }

    @Override
    public AuditLog save(AuditLog auditLog) {
        String sql = """
            INSERT INTO audit_log (user_id, table_name, action, created_at)
            VALUES (?, ?, ?, ?)
        """;
        jdbcTemplate.update(sql,
                auditLog.getUserId(),
                auditLog.getTableName(),
                auditLog.getAction().name(),
                auditLog.getCreatedAt()
        );
        return auditLog;
    }

    @Override
    public Optional<AuditLog> findById(Long id) {
        try {
            String sql = "SELECT * FROM audit_log WHERE id = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, auditLogRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<AuditLog> findByUserIdOrderByCreatedAtDesc(Long userId, int offset, int limit) {
        String sql = "SELECT * FROM audit_log WHERE user_id = ? ORDER BY created_at DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, auditLogRowMapper, userId, limit, offset);
    }

    @Override
    public List<AuditLog> findByTableNameOrderByCreatedAtDesc(String tableName, int offset, int limit) {
        String sql = "SELECT * FROM audit_log WHERE table_name = ? ORDER BY created_at DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, auditLogRowMapper, tableName, limit, offset);
    }

    @Override
    public List<AuditLog> findByActionOrderByCreatedAtDesc(AuditAction action, int offset, int limit) {
        String sql = "SELECT * FROM audit_log WHERE action = ? ORDER BY created_at DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, auditLogRowMapper, action.name(), limit, offset);
    }

    @Override
    public List<AuditLog> findByCreatedAtBetweenOrderByCreatedAtDesc(LocalDateTime start, LocalDateTime end, int offset, int limit) {
        String sql = "SELECT * FROM audit_log WHERE created_at BETWEEN ? AND ? ORDER BY created_at DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, auditLogRowMapper, start, end, limit, offset);
    }

    @Override
    public List<AuditLog> findAll(int offset, int limit) {
        String sql = "SELECT * FROM audit_log ORDER BY created_at DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, auditLogRowMapper, limit, offset);
    }

    @Override
    public long countAll() {
        String sql = "SELECT COUNT(*) FROM audit_log";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public long countByUserId(Long userId) {
        String sql = "SELECT COUNT(*) FROM audit_log WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, userId);
    }
}

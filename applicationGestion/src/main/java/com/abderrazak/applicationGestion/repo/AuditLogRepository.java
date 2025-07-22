package com.abderrazak.applicationGestion.repo;

import com.abderrazak.applicationGestion.model.AuditAction;
import com.abderrazak.applicationGestion.model.AuditLog;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface AuditLogRepository {
    AuditLog save(AuditLog auditLog);
    Optional<AuditLog> findById(Long id);
    List<AuditLog> findByUserIdOrderByCreatedAtDesc(Long userId, int offset, int limit);
    List<AuditLog> findByTableNameOrderByCreatedAtDesc(String tableName, int offset, int limit);
    List<AuditLog> findByActionOrderByCreatedAtDesc(AuditAction action, int offset, int limit);
    List<AuditLog> findByCreatedAtBetweenOrderByCreatedAtDesc(LocalDateTime start, LocalDateTime end, int offset, int limit);
    List<AuditLog> findAll(int offset, int limit);
    long countAll();
    long countByUserId(Long userId);
}

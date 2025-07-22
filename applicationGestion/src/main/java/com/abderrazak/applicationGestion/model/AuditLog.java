package com.abderrazak.applicationGestion.model;

import java.io.Serializable;
import java.time.LocalDateTime;


public class AuditLog {

    private long id;
    private long user_id;
    private String table_name;
    private AuditAction action;
    private LocalDateTime created_at;


    public AuditLog() {
    }

    public AuditLog(long id, long user_id, String table_name, AuditAction action, LocalDateTime created_at) {
        this.id = id;
        this.user_id = user_id;
        this.table_name = table_name;
        this.action = action;
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public AuditAction getAction() {
        return action;
    }

    public void setAction(AuditAction action) {
        this.action = action;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}

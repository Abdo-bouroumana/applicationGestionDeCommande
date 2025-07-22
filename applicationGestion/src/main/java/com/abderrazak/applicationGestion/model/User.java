package com.abderrazak.applicationGestion.model;


import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class User {

    private long id;
    private String username;
    private String email;
    private String password;
    private Role role;
    private boolean is_active;
    private boolean first_login;
    private LocalDateTime created_at;

    public User() {
    }

    public User(long id, String username, String email, String password, Role role, LocalDateTime created_at) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.first_login = true;
        this.is_active = true;
        this.created_at = created_at;
    }

    public User(long id, String email, String username, String password, Role role, boolean is_active, boolean first_login, LocalDateTime created_at) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.is_active = is_active;
        this.first_login = first_login;
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean getFirst_login() {
        return first_login;
    }

    public void setFirst_login(boolean first_login) {
        this.first_login = first_login;
    }

    public boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", is_active=" + is_active +
                ", first_login=" + first_login +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}

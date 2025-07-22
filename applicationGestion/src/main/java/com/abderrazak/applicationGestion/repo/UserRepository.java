package com.abderrazak.applicationGestion.repo;

import com.abderrazak.applicationGestion.model.Role;
import com.abderrazak.applicationGestion.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    List<User> findAll();
    List<User> findAllByIsActive(boolean is_active);
    List<User> findAllByRole(Role role);
    boolean update(User user);
    boolean deleteById(Long id);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    long countByIsActiveTrue();
    long countAll();
}

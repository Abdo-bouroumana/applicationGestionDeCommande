package com.abderrazak.applicationGestion.repo;

import com.abderrazak.applicationGestion.model.Role;
import com.abderrazak.applicationGestion.model.Users;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Users save(Users user);
    Optional<Users> findById(Long id);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByUsername(String username);
    List<Users> findAll(int offset, int limit);
    List<Users> findAllByIsActiveTrue(int offset, int limit);
    List<Users> findAllByRole(Role role, int offset, int limit);
    boolean update(Users user);
    boolean deleteById(Long id);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    long countByIsActiveTrue();
    long countAll();
}

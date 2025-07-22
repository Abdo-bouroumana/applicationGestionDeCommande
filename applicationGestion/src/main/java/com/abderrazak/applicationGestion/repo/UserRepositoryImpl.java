package com.abderrazak.applicationGestion.repo;

import com.abderrazak.applicationGestion.model.Role;
import com.abderrazak.applicationGestion.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    @Override
    public Optional<User> save(User user) {
        String sql = """
            INSERT INTO users (username, email, password, role)
            VALUES (?, ?, ?, ?)
        """;
        jdbcTemplate.update(sql,
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().name()
        );
        return findByUsername(user.getUsername());
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            String sql = "SELECT * FROM users WHERE id = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, userRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, userRowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, userRowMapper, username));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users ";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public List<User> findAllByIsActive(boolean is_active) {
        String sql = "SELECT * FROM users WHERE is_active = ?";
        return jdbcTemplate.query(sql, userRowMapper, is_active);
    }

    @Override
    public List<User> findAllByRole(Role role) {
        String sql = "SELECT * FROM users WHERE role = ? ";
        return jdbcTemplate.query(sql, userRowMapper, role.name());
    }

    @Override
    public boolean update(User user) {
        String sql = """
            UPDATE users SET username = ?, email = ?, password = ?, role = ?, 
            is_active = ?, first_login = ?, created_at = ? WHERE id = ?
        """;
        int rows = jdbcTemplate.update(sql,
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().name(),
                user.getIs_active(),
                user.getFirst_login(),
                user.getCreated_at(),
                user.getId()
        );
        return rows > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "UPDATE users SET is_deleted = true WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    public boolean existsByUsername(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }

    @Override
    public long countByIsActiveTrue() {
        String sql = "SELECT COUNT(*) FROM users WHERE is_active = true";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public long countAll() {
        String sql = "SELECT COUNT(*) FROM users";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }
}

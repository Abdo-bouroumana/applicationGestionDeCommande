package com.abderrazak.applicationGestion.repo;

import com.abderrazak.applicationGestion.model.Role;
import com.abderrazak.applicationGestion.model.Users;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    @Override
    public Users save(Users user) {
        String sql = """
            INSERT INTO users (username, email, password, role, is_active, first_login)
            VALUES (?, ?, ?, ?, ?, ?)
        """;
        jdbcTemplate.update(sql,
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().name(),
                user.getIs_active(),
                user.getFirst_login()
        );
        return user;
    }

    @Override
    public Optional<Users> findById(Long id) {
        try {
            String sql = "SELECT * FROM users WHERE id = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, userRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, userRowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Users> findByUsername(String username) {
        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, userRowMapper, username));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Users> findAll(int offset, int limit) {
        String sql = "SELECT * FROM users LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, userRowMapper, limit, offset);
    }

    @Override
    public List<Users> findAllByIsActiveTrue(int offset, int limit) {
        String sql = "SELECT * FROM users WHERE is_active = true LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, userRowMapper, limit, offset);
    }

    @Override
    public List<Users> findAllByRole(Role role, int offset, int limit) {
        String sql = "SELECT * FROM users WHERE role = ? LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, userRowMapper, role.name(), limit, offset);
    }

    @Override
    public boolean update(Users user) {
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
        String sql = "DELETE FROM users WHERE id = ?";
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

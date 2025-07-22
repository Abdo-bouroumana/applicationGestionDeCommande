package com.abderrazak.applicationGestion.service;

import com.abderrazak.applicationGestion.model.Role;
import com.abderrazak.applicationGestion.model.User;
import com.abderrazak.applicationGestion.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    public List<User> getUsersByRole(Role role) {
        List<User> users = userRepository.findAllByRole(role);
        if (users.isEmpty()) {
            throw new RuntimeException("No users found with role: " + role);
        }
        return users;
    }

    public boolean updateUser(User newUserInfo){
        return userRepository.update(newUserInfo);
    }

    public boolean deleteUserById(Long id) {
        return userRepository.deleteById(id);
    }

    public List<User> getActiveUsers() {
        return userRepository.findAllByIsActive(true);
    }

    public List<User> getNotActiveUsers() {
        return userRepository.findAllByIsActive(false);
    }



}

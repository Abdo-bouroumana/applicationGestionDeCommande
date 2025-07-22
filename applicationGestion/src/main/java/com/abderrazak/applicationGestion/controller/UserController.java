package com.abderrazak.applicationGestion.controller;

import com.abderrazak.applicationGestion.model.Role;
import com.abderrazak.applicationGestion.model.User;
import com.abderrazak.applicationGestion.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
        User user = userService.getUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("id") Long id){
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
        User user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findByRole/{role}")
    public ResponseEntity<List<User>> getUserByEmail(@PathVariable("role")Role role){
        List<User> users = userService.getUsersByRole(role);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/findActiveUsers")
    public ResponseEntity<List<User>> getActiveUsers(){
        List<User> users = userService.getActiveUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/findNotActiveUsers")
    public ResponseEntity<List<User>> getNotActiveUsers(){
        List<User> users = userService.getNotActiveUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Optional<User>> createUser(@RequestBody User user){
        Optional<User> newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        boolean updatedUser = userService.updateUser(user);
        if (updatedUser) {
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or update failed");
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

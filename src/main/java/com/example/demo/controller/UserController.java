package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("Users not found");
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long userId) {
        Optional<User> user = Optional.ofNullable(userService.getUserById(userId));
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User you are trying to find is not in database with userId " + userId);
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{emailId}")
    public ResponseEntity<Optional<User>> getUserByEmailName(@PathVariable String emailId) {
        Optional<User> user = Optional.ofNullable(userService.getUserByEmail(emailId));
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User you are trying to find is not in database with emailId " + emailId);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Optional<User>> updateUser(@PathVariable Long userId, @RequestBody User user) {
        Optional<User> existingUser = Optional.ofNullable(userService.getUserById(userId));
        if (existingUser.isEmpty()) {
            throw new ResourceNotFoundException("User you are trying to find is not in database with userId " + userId);
        }
        User updatedUser = existingUser.get();
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        Optional<User> userUpdated = Optional.ofNullable(userService.updateUser(updatedUser));
        if (userUpdated.isEmpty()) {
            throw new ResourceNotFoundException("User not able to update " + userId);
        }
        return ResponseEntity.ok(userUpdated);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable Long userId) {
        Optional<User> user = Optional.ofNullable(userService.getUserById(userId));
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User you are trying to delete is not in database with userId " + userId);
        }
        userService.deleteUserById(userId);
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
}

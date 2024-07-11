package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.entity.User;
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
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long userId) {
        Optional<User> user = Optional.ofNullable(userService.getUserById(userId));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{emailId}")
    public ResponseEntity<Optional<User>> getUserByEmailName(@PathVariable String emailId) {
        Optional<User> user = Optional.ofNullable(userService.getUserByEmail(emailId));
        return ResponseEntity.ok(user);
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        System.out.println("created user " + createdUser);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Optional<User>> updateUser(@PathVariable Long userId, @RequestBody User user) {
        Optional<User> userUpdated = Optional.ofNullable(userService.updateUser(userId,user));
        System.out.println("updated user " + userUpdated);
        return ResponseEntity.ok(userUpdated);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable Long userId) {
        if(userService.deleteUserById(userId)){
            return ResponseEntity.ok(userService.getAllUsers());
        }
        return null;
    }
}

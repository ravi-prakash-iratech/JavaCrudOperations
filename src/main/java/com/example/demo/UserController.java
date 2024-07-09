package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{userId}")
    public Optional<User> getUserById(@PathVariable Long userId){
        Optional<User> x = userRepository.findById(userId);
        System.out.println("getbyid" + x);
        return x;
    }

    @GetMapping("/{emailId}")
    public User getUserByEmailName(@PathVariable String emailId){
        return userService.getUserByEmail(emailId);
    }

    @PostMapping("")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{userId}")
    public Optional<User> UpdateUser(@PathVariable Long userId, @RequestBody User user) {
        //System.out.println();
        Optional<User> existingUser = userRepository.findById(userId);

        // if not present create user
        if (existingUser.isEmpty()) {
            //create user
            User newUser = new User();
            newUser.setEmail(user.getEmail());
            newUser.setName(user.getName());

            return Optional.of(userService.createUser(newUser));
        }
        //update user
        User updatedUser = existingUser.get();
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        userRepository.save(updatedUser);
        return existingUser;
    }

    @DeleteMapping("/{userId}")
    public List<User>  deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return userService.getAllUsers();
    }
}


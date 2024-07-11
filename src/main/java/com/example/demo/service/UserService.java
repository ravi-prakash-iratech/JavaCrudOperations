package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("Users not found");
        }
        return users;
    }

    public User getUserByEmail(String emailId) {
        User user = userRepository.findUserByEmail(emailId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with email id " + emailId);
        }
        return user;
    }

    public User getUserById(Long userId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User you are trying to find is not in database with userId " + userId);
        }
        return user;
    }

    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        if (savedUser.getId() == null) {
            throw new BadRequestException("User is not saved in database");
        }
        return savedUser;
    }

    public User updateUser(Long userId, User user) {
        //find from db
        User existingUser = userRepository.findUserById(userId);
        if (existingUser == null) {
            throw new ResourceNotFoundException("User you are trying to update is not in database with userId " + userId);
        }
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());

        //update in db
        User savedUser = userRepository.save(existingUser);
        System.out.println("savedUser" + savedUser);
        if (savedUser.getId() == null) {
            throw new BadRequestException("User is not updated in database");
        }
        System.out.println("savedUserID" + savedUser.getId());
        return savedUser;
    }

    public boolean deleteUserById(Long userId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User you are trying to delete is not in database with userId " + userId);
        }
        userRepository.deleteById(userId);
        return true;
    }
}

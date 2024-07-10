package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User>  itr =userRepository.findAll();

        itr.forEach(u->{System.out.println("users ---> " + u);});
        return userRepository.findAll();
    }

    public User getUserByEmail(String emailId) {
        return userRepository.findUserByEmail(emailId);
    }

    public User getUserById(Long userId) {
        return userRepository.findUserById(userId);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}

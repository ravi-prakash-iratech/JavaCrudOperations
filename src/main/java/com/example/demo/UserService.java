package com.example.demo;

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
        return userRepository.findByEmail(emailId);
    }

//    public User getUserByUserId(Long userId) {
//        User x = userRepository.findUserByIdWithin(userId);
//        System.out.println("getbyid" + x);
//        return x;
//    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}

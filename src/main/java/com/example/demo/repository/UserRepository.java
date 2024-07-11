package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);

    User findUserById(Long userId);

    //Examples of native Query
    @Query(value = "select * from users where id = 7",nativeQuery = true)
    public List<User> findByQueryName();
}


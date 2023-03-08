package com.blog.blogapp.repository;

import com.blog.blogapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    //find user by email
    User findByEmail(String email);
}

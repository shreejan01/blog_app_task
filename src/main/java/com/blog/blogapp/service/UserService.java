package com.blog.blogapp.service;

import com.blog.blogapp.entity.User;
import com.blog.blogapp.entity.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {

    //creating user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //Update user
    User updateUser(User user);

    //get user by email
    public User getUserByEmail(String email);

    //Get user by id
    User getUserById(Long userId) ;

    //delete by id
    public void deleteUserById(Long userId);

    //get all users
    public List<User> getAllUsers();
}

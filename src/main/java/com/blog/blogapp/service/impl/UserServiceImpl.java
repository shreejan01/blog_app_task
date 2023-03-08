package com.blog.blogapp.service.impl;

import com.blog.blogapp.entity.User;
import com.blog.blogapp.entity.UserRole;
import com.blog.blogapp.exception.ResourceNotFoundException;
import com.blog.blogapp.repository.RoleRepository;
import com.blog.blogapp.repository.UserRepository;
import com.blog.blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    //create user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local =  this.userRepo.findByEmail(user.getEmail());

        if(local != null) {
            System.out.println("User with this email is already created !!");
            throw new ResourceNotFoundException("User","email", user.getEmail());
        } else {

            //Create User
            for(UserRole ur:  userRoles) {
                roleRepo.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            local = this.userRepo.save(user);

        }

        return local;
    }

    //update user
    @Override
    public User updateUser(User user) {
        return this.userRepo.save(user);
    }

    //get user by email
    @Override
    public User getUserByEmail(String email) {
        User user = this.userRepo.findByEmail(email);
        if (user==null) {
            throw  new ResourceNotFoundException("User","email",email);
        } else {
            return user;
        }
    }

    //get user by id
    @Override
    public User getUserById(Long userId)  {
        return this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
    }

    //delete user by id
    @Override
    public void deleteUserById(Long userId) {
        this.userRepo.deleteById(userId);
    }

    //get all user list
    @Override
    public List<User> getAllUsers() {
        return this.userRepo.findAll();
    }
}

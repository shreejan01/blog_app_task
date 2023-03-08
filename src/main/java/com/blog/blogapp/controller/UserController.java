package com.blog.blogapp.controller;

import com.blog.blogapp.entity.Role;
import com.blog.blogapp.entity.User;
import com.blog.blogapp.entity.UserRole;
import com.blog.blogapp.service.FileService;
import com.blog.blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private FileService fileService;

    @Value("${project.profile}")
    private String path;


    //Creating user
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        //encoding password with Bcryptpasswordencoder
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        user.setFullName(user.getFullName().trim());
        user.setAddress(user.getAddress().trim());

        Set<UserRole> roles = new HashSet<>();

        Role role = new Role();
        role.setRoleId(2L);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);

        return this.userService.createUser(user,roles);
    }


    //upload profile image
    @PostMapping("/upload/profile/{userId}")
    public ResponseEntity<User> uploadPost(@RequestParam("file") MultipartFile file, @PathVariable("userId") Long userId) throws IOException {
        User user = this.userService.getUserById(userId);
        String name = this.fileService.uploadImage(path,file);
        user.setProfile(name);
        User user1 = this.userService.updateUser(user);
        return ResponseEntity.ok(user1);
    }

    //Updating user
    @PostMapping("/update")
    public User updateUser(@RequestBody User user) throws  Exception {
        User user1 = this.userService.getUserById(user.getId());
        user1.setFullName(user.getFullName());
        user1.setAddress(user.getAddress());
        return  this.userService.updateUser(user1);
    }

    //Get user by email
    @GetMapping("/{email}")
    public ResponseEntity<User> getUser(@PathVariable("email") String email) {

        return new ResponseEntity<>(this.userService.getUserByEmail(email),HttpStatus.OK) ;
    }

    //Get user by id
    @GetMapping("/id/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) throws Exception {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

    //Delete user by id
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {

        this.userService.deleteUserById(userId);
    }

    //Get all users
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(this.userService.getAllUsers(),HttpStatus.OK) ;
    }

}

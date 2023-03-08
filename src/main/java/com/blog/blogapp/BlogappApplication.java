package com.blog.blogapp;

import com.blog.blogapp.entity.Answers;
import com.blog.blogapp.entity.Role;
import com.blog.blogapp.entity.User;
import com.blog.blogapp.entity.UserRole;
import com.blog.blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BlogappApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BlogappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Answers.main(null);


        System.out.println("Starting code...");

		try {

			User user = new User();
			user.setFullName("Shreejan Lohar");
			user.setEmail("shreejan.lohar@gmail.com");
			user.setPassword(this.bCryptPasswordEncoder.encode("Shreejan"));
			user.setAddress("Kathmandu");
			user.setProfile("shree.jpg");

			Role role1 = new Role();
			role1.setRoleId(1L);
			role1.setRoleName("Admin");

			Set<UserRole> userRoleSet = new HashSet<>();
			UserRole userRole = new UserRole();
			userRole.setRole(role1);
			userRole.setUser(user);

			userRoleSet.add(userRole);

			User user1 = this.userService.createUser(user, userRoleSet);

			System.out.println(user1.getEmail());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package com.blog.blogapp.repository;

import com.blog.blogapp.entity.Post;
import com.blog.blogapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    //find post by user and status
    List<Post> findByUserAndStatus(User user, boolean b);

    //find post by status
    List<Post> findByStatus(boolean b);
}

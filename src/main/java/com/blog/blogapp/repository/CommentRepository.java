package com.blog.blogapp.repository;

import com.blog.blogapp.entity.Comment;
import com.blog.blogapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //get comments of post
    List<Comment> findByPostAndStatus(Post post, boolean b);
}

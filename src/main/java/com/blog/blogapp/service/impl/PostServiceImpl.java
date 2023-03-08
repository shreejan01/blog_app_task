package com.blog.blogapp.service.impl;

import com.blog.blogapp.entity.Post;
import com.blog.blogapp.entity.User;
import com.blog.blogapp.exception.ResourceNotFoundException;
import com.blog.blogapp.repository.PostRepository;
import com.blog.blogapp.repository.UserRepository;
import com.blog.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostRepository postRepo;

   //create post
    @Override
    public Post createPost(Post post, Long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        post.setCreatedDate(Instant.now());
       // post.setCreatedDuration(TimeAgo.using(post.getCreatedDate().toEpochMilli()));
        post.setUser(user);
        return this.postRepo.save(post);
    }

    //update post
    @Override
    public Post updatePost(Post post) {
        return this.postRepo.save(post);
    }

    //get all posts
    @Override
    public List<Post> getAllPost() {
        return this.postRepo.findAll();
    }

    //get all active posts
    @Override
    public List<Post> getAllActivePost() {
        return this.postRepo.findByStatus(true);
    }

    //get post by id
    @Override
    public Post getPostById(Long postId) {
        return this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post ","id", postId));
    }

    //get active post of user
    @Override
    public List<Post> getActivePostOfUser(Long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        List<Post> postList = this.postRepo.findByUserAndStatus(user,true);
        return postList.stream().sorted(Comparator.comparing(Post::getCreatedDate,Comparator.nullsLast(Comparator.reverseOrder()))).collect(Collectors.toList());
    }

    //get private post of user
    @Override
    public List<Post> getPrivatePostOfUser(Long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        List<Post> postList = this.postRepo.findByUserAndStatus(user,false);
        return postList.stream().sorted(Comparator.comparing(Post::getCreatedDate,Comparator.nullsLast(Comparator.reverseOrder()))).collect(Collectors.toList());
    }

}

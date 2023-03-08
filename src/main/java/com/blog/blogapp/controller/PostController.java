package com.blog.blogapp.controller;

import com.blog.blogapp.entity.Post;
import com.blog.blogapp.entity.User;
import com.blog.blogapp.service.FileService;
import com.blog.blogapp.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/post")
@CrossOrigin("*")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.post}")
    private String path;

    //create post
    @PostMapping("/{userId}")
    public ResponseEntity<Post> addPost( @RequestParam("file") MultipartFile file, @RequestParam(required = false) String post , @PathVariable("userId") Long userId) throws IOException {
        Post post1 = new ObjectMapper().readValue(post,Post.class);
       //create post
        Post post2 = this.postService.createPost(post1,userId);
        //upload post image
           String name = this.fileService.uploadImage(path,file);
           post2.setPostImage(name);
           //update post with image
           Post post3 = this.postService.updatePost(post2);
       return new ResponseEntity<>(post3, HttpStatus.OK);
    }

    //upload profile image
    @PostMapping("/upload/post/{postId}")
    public ResponseEntity<Post> uploadPost(@RequestParam("file") MultipartFile file, @PathVariable("postId") Long postId) throws IOException {
        Post post = this.postService.getPostById(postId);
        String name = this.fileService.uploadImage(path,file);
        post.setPostImage(name);
        Post post1 = this.postService.updatePost(post);
        return ResponseEntity.ok(post1);
    }

    //update post
    @PostMapping("/update")
    public ResponseEntity<?> updatePost(@RequestBody Post post) {
        Post post1 = this.postService.getPostById(post.getId());
        post1.setDescription(post.getDescription());
        return  ResponseEntity.ok(this.postService.updatePost(post1));
    }

    //get all posts
    @GetMapping("/")
    public ResponseEntity<?> getAllPost() {
        return ResponseEntity.ok(this.postService.getAllPost());
    }

    //get all active posts
    @GetMapping("/all/active")
    public ResponseEntity<?> getAllActivePost() {
        return ResponseEntity.ok(this.postService.getAllActivePost());
    }

    //get post by id
    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable("postId") Long postId) {
        return  ResponseEntity.ok(this.postService.getPostById(postId));
    }


    //get active post of user
    @GetMapping("/active/user/{userId}")
    public ResponseEntity<?> getActivePostOfUser(@PathVariable("userId") Long userId) {
        return   ResponseEntity.ok(this.postService.getActivePostOfUser(userId));
    }

    //get private post of user
    @GetMapping("/private/user/{userId}")
    public ResponseEntity<?> getPrivatePostOfUser(@PathVariable("userId") Long userId) {
        return  ResponseEntity.ok(this.postService.getPrivatePostOfUser(userId));
    }
}

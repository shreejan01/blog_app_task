package com.blog.blogapp.service;

import com.blog.blogapp.entity.Post;

import java.util.List;

public interface PostService {

    //create post
    Post createPost(Post post, Long userId) ;

    //update post
    Post updatePost(Post post);

    //get all post
    List<Post> getAllPost();

    //get all active posts
    List<Post> getAllActivePost();

    //get post by Id
    Post getPostById(Long postId);

    //get active post of user
    List<Post> getActivePostOfUser(Long userId) ;

    //get private post of user
    List<Post> getPrivatePostOfUser(Long userId) ;
}

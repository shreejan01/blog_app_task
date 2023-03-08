package com.blog.blogapp.service;

import com.blog.blogapp.entity.Comment;

import java.util.List;

public interface CommentService {

    //create comment
    Comment createComment(Comment comment, Long postId, Long userId) throws  Exception;

    //update comment
    Comment updateComment(Comment comment);

    //get all comments of post
    List<Comment> geCommentsOfPost(Long postId, Long currentUserId);

    //get all comments
    List<Comment> getAllComments();

    //get comment by id
    Comment getCommentById(Long commentId);

    //delete comment
    void deleteComment(Long commentId);

    //delete comment by status (soft delete)
    Comment deleteByStatus(Long commentId);
}

package com.blog.blogapp.service.impl;

import com.blog.blogapp.entity.Comment;
import com.blog.blogapp.entity.Post;
import com.blog.blogapp.entity.User;
import com.blog.blogapp.exception.ResourceNotFoundException;
import com.blog.blogapp.repository.CommentRepository;
import com.blog.blogapp.repository.PostRepository;
import com.blog.blogapp.repository.UserRepository;
import com.blog.blogapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepository userRepo;

    //create comment of post
    @Override
    public Comment createComment(Comment comment, Long postId, Long userId) throws  Exception {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        if (post.getCommentCount()!=null) {
            post.incrementComment();
        }
        comment.setPost(post);
        comment.setUser(user);
        comment.setCreatedDate(Instant.now());
        comment.setStatus(true);

        return this.commentRepo.save(comment);
    }

    //update comment
    @Override
    public Comment updateComment(Comment comment) {
        return this.commentRepo.save(comment);
    }

    //get all comments of post
    @Override
    public List<Comment> geCommentsOfPost(Long postId, Long currentUserId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));
        List<Comment> commentList = this.commentRepo.findByPostAndStatus(post,true);
        return commentList.stream().sorted(Comparator.comparing(Comment::getCreatedDate,Comparator.nullsLast(Comparator.reverseOrder()))).collect(Collectors.toList());
    }


    //get all comments
    @Override
    public List<Comment> getAllComments() {
        return this.commentRepo.findAll();
    }

    //get comment by id
    @Override
    public Comment getCommentById(Long commentId) {
        return this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));
    }

    //delete comment by id
    @Override
    public void deleteComment(Long commentId) {
        this.commentRepo.deleteById(commentId);
    }

    //delete comment by status
    @Override
    public Comment deleteByStatus(Long commentId) {
        Comment comment = this.getCommentById(commentId);
        Post post = this.postRepo.findById(comment.getPost().getId()).orElseThrow(()->new ResourceNotFoundException("Post","id",comment.getPost().getId()));
        comment.setStatus(false);
        if (post.getCommentCount()!=null) {
            post.decrementCommentCount();
        }
        comment.setPost(post);
        return this.commentRepo.save(comment);
    }
}
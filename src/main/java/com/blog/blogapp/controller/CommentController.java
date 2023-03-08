package com.blog.blogapp.controller;

import com.blog.blogapp.entity.Comment;
import com.blog.blogapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //create comment of post
    @PostMapping("/create/{postId}/{userId}")
    public ResponseEntity<?> createComment(@RequestBody Comment comment, @PathVariable("postId") Long postId, @PathVariable("userId") Long userId) throws Exception {
        return  ResponseEntity.ok(this.commentService.createComment(comment,postId,userId));
    }

    //get all comments
    @GetMapping("/all")
    public ResponseEntity<?> getAllComments() {
        return ResponseEntity.ok(this.commentService.getAllComments());
    }

    //get all comments of post of current user
    @GetMapping("/list/{postId}/{currentUserId}")
    public ResponseEntity<?> getCommentsOfPost(@PathVariable("postId") Long postId, @PathVariable("currentUserId") Long currentUserId) {
        return ResponseEntity.ok(this.commentService.geCommentsOfPost(postId,currentUserId));
    }

    //update comment
    @PostMapping("/update")
    public ResponseEntity<?> updateComment(@RequestBody Comment comment) {
        Comment comment1 = this.commentService.getCommentById(comment.getId());
        comment1.setContent(comment.getContent());

        return ResponseEntity.ok(this.commentService.updateComment(comment1));
    }

    //get comment by id
    @GetMapping("/id/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("commentId") Long commentId) {
        return new ResponseEntity<>(this.commentService.getCommentById(commentId), HttpStatus.OK);
    }

    //delete comment by id
    @DeleteMapping("/delete/{commentId}")
    public void deleteComment(@PathVariable("commentId") Long commentId) {
        this.commentService.deleteComment(commentId);
    }

    //delete comment by status
    @GetMapping("/delete/status/{commentId}")
    public ResponseEntity<?> deleteCommentByStatus(@PathVariable("commentId") Long commentId) {
        return  ResponseEntity.ok(this.commentService.deleteByStatus(commentId));
    }
}


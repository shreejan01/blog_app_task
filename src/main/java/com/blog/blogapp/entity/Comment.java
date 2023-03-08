package com.blog.blogapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;
    private boolean status = true;
    private Instant createdDate;
    private String duration;

    //many comments have one post
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Post post;

    //many comments have one user
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Comment() {
    }

    public Comment(Long id, String content, boolean status, Instant createdDate, String duration, Post post, User user) {
        this.id = id;
        this.content = content;
        this.status = status;
        this.createdDate = createdDate;
        this.duration = duration;
        this.post = post;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", createdDate=" + createdDate +
                ", duration='" + duration + '\'' +
                ", post=" + post +
                ", user=" + user +
                '}';
    }
}

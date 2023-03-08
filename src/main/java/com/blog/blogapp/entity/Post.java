package com.blog.blogapp.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String postTitle;
    private String description;
    private String postImage;
    private Instant createdDate;
    private String createdDuration;
    private AtomicLong commentCount = new AtomicLong(0L);
    private boolean status = true;

    //many posts has one user
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "post")
    private Set<Comment> comments = new HashSet<>();

    public Post() {
    }

    public Post(Long id, String postTitle, String description, String postImage, Instant createdDate, String createdDuration, AtomicLong commentCount, boolean status, User user, Set<Comment> comments) {
        this.id = id;
        this.postTitle = postTitle;
        this.description = description;
        this.postImage = postImage;
        this.createdDate = createdDate;
        this.createdDuration = createdDuration;
        this.commentCount = commentCount;
        this.status = status;
        this.user = user;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedDuration() {
        return createdDuration;
    }

    public void setCreatedDuration(String createdDuration) {
        this.createdDuration = createdDuration;
    }

    public AtomicLong getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(AtomicLong commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", postTitle='" + postTitle + '\'' +
                ", description='" + description + '\'' +
                ", postImage='" + postImage + '\'' +
                ", createdDate=" + createdDate +
                ", createdDuration='" + createdDuration + '\'' +
                ", commentCount=" + commentCount +
                ", status=" + status +
                ", user=" + user +
                ", comments=" + comments +
                '}';
    }

    public void incrementComment() {
        commentCount.incrementAndGet();
    }

    public void decrementCommentCount() {
        commentCount.decrementAndGet();
    }

}

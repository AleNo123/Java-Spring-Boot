package com.example.javaProj.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import jakarta.persistence.Entity;

import java.time.OffsetDateTime;

@Entity
@Table(name = "comment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    public Comment() {
    }

    public Comment(Long commentId, Post post, Profile profile, OffsetDateTime dateCommented) {
        this.commentId = commentId;
        this.post = post;
        this.profile = profile;
        this.dateCommented = dateCommented;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setDateCommented(OffsetDateTime dateCommented) {
        this.dateCommented = dateCommented;
    }

    public Long getCommentId() {
        return commentId;
    }

    public Post getPost() {
        return post;
    }

    public Profile getProfile() {
        return profile;
    }

    public OffsetDateTime getDateCommented() {
        return dateCommented;
    }

    @Column(name = "date_commented", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateCommented;


}
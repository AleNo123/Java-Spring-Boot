package com.example.javaProj.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


import java.time.OffsetDateTime;


@Entity
@Table(name = "post")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "title", length = 200, nullable = false)
    private String title;
    @Column(name = "content", nullable = false)
    private String content;

    public Post() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @Column(name = "date_added", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateAdded;
    public Post(Long postId, String title, String content, Profile profile, OffsetDateTime dateAdded) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.profile = profile;
        this.dateAdded = dateAdded;
    }

    public Long getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Profile getProfile() {
        return profile;
    }

    public OffsetDateTime getDateAdded() {
        return dateAdded;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setDateAdded(OffsetDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }
}

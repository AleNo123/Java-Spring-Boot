package com.example.javaProj.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

@SequenceGenerator(name = "topic_seq", sequenceName = "topic_seq", allocationSize = 1)
@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_seq")
    private Long topicId;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private String description;

    @NotNull
    @NotBlank
    private String content;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateCreation;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateChanging;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_creater_id")
    private User UserCreater;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_changer_id")
    private User UserChanger;
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateDeleting;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_deleter_id")
    private User UserDeleter;

    @NotNull
    @NotBlank
    private String task;

    public Topic() {
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDateCreation(OffsetDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDateChanging(OffsetDateTime dateChanging) {
        this.dateChanging = dateChanging;
    }

    public void setUserCreater(User userCreater) {
        UserCreater = userCreater;
    }

    public void setUserChanger(User userChanger) {
        UserChanger = userChanger;
    }

    public void setDateDeleting(OffsetDateTime dateDeleting) {
        this.dateDeleting = dateDeleting;
    }

    public void setUserDeleter(User userDeleter) {
        UserDeleter = userDeleter;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Long getTopicId() {
        return topicId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public OffsetDateTime getDateCreation() {
        return dateCreation;
    }

    public OffsetDateTime getDateChanging() {
        return dateChanging;
    }

    public User getUserCreater() {
        return UserCreater;
    }

    public User getUserChanger() {
        return UserChanger;
    }

    public OffsetDateTime getDateDeleting() {
        return dateDeleting;
    }

    public User getUserDeleter() {
        return UserDeleter;
    }

    public String getTask() {
        return task;
    }
}

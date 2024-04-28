package com.example.javaProj.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

@Entity
@Table(name = "module")
@SequenceGenerator(name = "module_seq", sequenceName = "module_seq", allocationSize = 1)
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "module_seq")
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateCreation;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @OneToOne
    @JoinColumn(name = "topic")
    private Topic topic;

    public Module() {
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public Course getCourse() {
        return course;
    }
}

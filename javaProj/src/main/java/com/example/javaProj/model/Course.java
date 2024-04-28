package com.example.javaProj.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @NotNull
    @Column(name = "title")
    private String title;
    @Column(name = "description") // Новые поля
    private String description;

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

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Module> modules;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<User> registratedUsers;

    @NotNull
    @NotBlank
    private String tag;
    private String category;
    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public void setRegistratedUsers(Set<User> registratedUsers) {
        this.registratedUsers = registratedUsers;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
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

    public List<Rating> getRatings() {
        return ratings;
    }

    public List<Module> getModules() {
        return modules;
    }

    public Set<User> getRegistratedUsers() {
        return registratedUsers;
    }

    public String getTag() {
        return tag;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }
    public Course(){}

    public Course(Long id,String title, String author) {
        this.id = id;
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }
}

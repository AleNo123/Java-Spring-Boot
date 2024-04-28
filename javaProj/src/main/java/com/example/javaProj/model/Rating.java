package com.example.javaProj.model;

import jakarta.persistence.*;

import java.util.List;

@SequenceGenerator(name = "rating_seq", sequenceName = "rating_seq", allocationSize = 1)
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_seq")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_rated_id")
    private User userRated;
    private int mark;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    public Rating() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserRated(User userRated) {
        this.userRated = userRated;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public User getUserRated() {
        return userRated;
    }

    public int getMark() {
        return mark;
    }

    public Course getCourse() {
        return course;
    }
}

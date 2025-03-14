package com.example.javaProj.model;

import jakarta.persistence.*;

import java.util.List;

@SequenceGenerator(name = "rating_seq", sequenceName = "rating_seq", allocationSize = 1)
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_seq")
    private Long id;

    @OneToMany(mappedBy = "rating", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mark> marks;
    private int resultMark;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Course course;

    public Rating() {
    }
}

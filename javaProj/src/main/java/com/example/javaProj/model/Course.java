package com.example.javaProj.model;

import jakarta.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name = "title", nullable = false)
    String title;
    @Column(name = "author")
    String author;
}

package com.example.javaProj.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Course { //Пока старое из первого задания
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Column(name = "title")
    private String title;
    @NotNull
    @Column(name = "author")
    private String author;

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public Course(){}

    public Course(Long id,String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }
}

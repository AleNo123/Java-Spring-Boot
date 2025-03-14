package com.example.javaProj.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Scanner;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "report",cascade = CascadeType.PERSIST)
    private List<Task> tasks;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    private double completionRate; // Процент прохождения
    @NotNull
    @NotBlank
    private String type; // Тип (Course, Module, Topic)
    private Long numOfUsersBelongsToCourse;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = true)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = true)
    private Module module;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = true)
    private Topic topic;
}

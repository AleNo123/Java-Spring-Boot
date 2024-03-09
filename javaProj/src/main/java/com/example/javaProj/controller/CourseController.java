package com.example.javaProj.controller;

import com.example.javaProj.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    private final CourseRepository repository;
    @Autowired
    public CourseController(CourseRepository repository){
        this.repository = repository;
    }

}

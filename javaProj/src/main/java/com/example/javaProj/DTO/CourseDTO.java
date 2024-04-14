package com.example.javaProj.DTO;

import com.example.javaProj.model.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CourseDTO {
    @NotBlank
    private String title;
    @NotNull
    private String author;
    public CourseDTO(String title,String author){
        this.title=title;
        this.author=author;
    }
    public Course convertToCourse(){
        Course course = new Course();
        course.setAuthor(author);
        course.setTitle(title);
        return course;
    }
    public CourseDTO(Course course){
        this.author=course.getAuthor();
        this.title= course.getTitle();
    }

    public String getTitle() {
        return title;
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
}

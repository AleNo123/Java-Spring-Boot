package com.example.javaProj.DTO;

import com.example.javaProj.model.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CourseDTO {
    @NotBlank
    @NotNull
    private String title;
    public CourseDTO(String title,String author){
        this.title=title;
    }
    public Course convertToCourse(){
        Course course = new Course();
        course.setTitle(title);
        return course;
    }
    public CourseDTO(Course course){
        this.title= course.getTitle();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

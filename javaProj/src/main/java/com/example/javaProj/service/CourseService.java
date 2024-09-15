package com.example.javaProj.service;

import com.example.javaProj.DTO.CourseDTO;
import com.example.javaProj.model.Course;
import com.example.javaProj.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
@Service
public class CourseService {
    private final CourseRepository repository;
    @Autowired
    public CourseService(CourseRepository repository){ this.repository =repository;}
    public CourseDTO getById(Long id){
        return new CourseDTO(repository.getReferenceById(id));
    }
    public List<Course> getAllByTitle(String title){
        return repository.findByTitleContaining(title);
    }
    public List<Course> getAll(int page,int size){
        Pageable pageable = PageRequest.of(page, size);
        repository.findAll(pageable);
        return repository.findAll();
    }
    public void delete(Long id){
        repository.deleteById(id);
    }
    public void create(CourseDTO course){
        repository.save(course.convertToCourse());
    }
    public void set(Long id, CourseDTO courseDTO){
        Course course = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Course not found with id: " + id));
        course.setTitle(courseDTO.getTitle());
        repository.save(course);
    }
}

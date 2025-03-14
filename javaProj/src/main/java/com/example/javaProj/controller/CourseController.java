package com.example.javaProj.controller;

import com.example.javaProj.DTO.CourseDTO;
import com.example.javaProj.model.Course;
import com.example.javaProj.service.CourseService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;


@RestController
@EnableWebMvc
public class CourseController{
    private final CourseService service;
    private final Logger logger = LoggerFactory.getLogger(CourseController.class);
    @Autowired
    public CourseController(CourseService service){
        this.service = service;
    }
    @GetMapping("/search")
    public List<Course> getAllByTitle(@Valid @RequestParam String title){
        return service.getAllByTitle(title);
    }
//    @GetMapping("/{id}")
//    public CourseDTO getById(@PathVariable("id") Long id){
//        return service.getById(id);
//    }
//    @GetMapping
//    public List<Course> getAll(@PathVariable("page") int page,@PathVariable("size") int size){
//        return service.getAll(page,size);
//    }
//    @PostMapping("/create")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void create(@Valid @RequestBody CourseDTO request){
//        logger.debug(String.valueOf(request));
//        service.create(request);
//    }
//    @PutMapping("/{id}")
//    public void set(@PathVariable("id") Long id,@Valid @RequestBody CourseDTO request){
//        service.set(id,request);
//    }
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable("id") Long id){
//        service.delete(id);
//    }
}
// curl --header "Content-Type: application/json"   --request POST   --data '{"title": "title", "author": "author"}'   http://172.19.208.1:8080/course
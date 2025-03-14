package com.example.javaProj.controller;

import com.example.javaProj.model.Post;
import com.example.javaProj.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@RequestMapping("/post")
@EnableWebMvc
public class PostController {
    private final PostService service;

    @Autowired
    public PostController(PostService service) {
        this.service = service;
    }
    @GetMapping
    public List<Post> getAllPost(){
        return service.getAll();
    }
    @GetMapping("/no-post")
    public int getCountProfileWithoutPost(){
        return service.getCountProfileWithoutPost();
    }
    @GetMapping("/filter")
    public List<Long> getPostIdWithFilter(){
        return service.getPostIdWithFilter();
    }
    @GetMapping("/3id")
    public List<Long> get3PostIdWithoutComment(){
        return service.get3PostIdWithoutComment();
    }
}

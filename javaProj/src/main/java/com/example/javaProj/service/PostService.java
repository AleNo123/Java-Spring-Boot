package com.example.javaProj.service;

import com.example.javaProj.model.Post;
import com.example.javaProj.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    final private PostRepository repository;

    @Autowired
    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public int getCountProfileWithoutPost(){
        return repository.getCountProfileWithoutPost();
    }
    public List<Post> getAll(){
        return repository.findAll();
    }

//    public List<Long> getPostIdWithFilter() {
//        return repository.getPostIdWithFilter();
//    }
//    public List<Long> get3PostIdWithoutComment() {
//        return repository.get3PostIdWithoutComment();
//    }
}

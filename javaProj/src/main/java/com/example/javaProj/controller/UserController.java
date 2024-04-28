package com.example.javaProj.controller;

import com.example.javaProj.DTO.UserRequestDTO;
import com.example.javaProj.View.UserView;
import com.example.javaProj.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/user")
@EnableWebMvc
public class UserController {
    private final UserService service;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    public UserController(UserService service){
        this.service = service;
    }


    @GetMapping("/{id}")
    public UserView getUserById(@PathVariable("id") Long id){
        logger.warn(service.getUserById(id).toString());
        return service.getUserById(id);
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody UserRequestDTO requestDTO){
        service.createUser(requestDTO);
    }
}

//    @PostMapping("/create")
//    @ResponseStatus(HttpStatus.CREATED)
//
//    @PutMapping("/{id}")
//
//    @DeleteMapping("/{id}")
//}

package com.example.javaProj.DTO;


import jakarta.validation.constraints.NotBlank;

public class PostDTO {
    @NotBlank
    private String title;
    private String content;
}

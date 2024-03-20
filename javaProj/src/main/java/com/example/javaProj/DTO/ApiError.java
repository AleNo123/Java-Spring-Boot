package com.example.javaProj.DTO;

import com.example.javaProj.model.Course;

import java.time.OffsetDateTime;

public class ApiError {
    private String message;
    private OffsetDateTime dateOccurred;

    public String getMessage() {
        return message;
    }

    public OffsetDateTime getDateOccurred() {
        return dateOccurred;
    }

    public ApiError(String message, OffsetDateTime dateOccurred){
        this.message=message;
    }
}
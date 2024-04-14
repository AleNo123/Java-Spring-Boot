package com.example.javaProj.DTO;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class ApiError {
    private final String message;
    private final OffsetDateTime dateOccurred;

    public String getMessage() {
        return message;
    }

    public String getDateOccurred() {
        return dateOccurred.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a"));
    }

    public ApiError(String message, OffsetDateTime dateOccurred){
        this.message=message;
        this.dateOccurred=dateOccurred;
    }
}
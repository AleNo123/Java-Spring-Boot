package com.example.javaProj.controller;

import com.example.javaProj.DTO.ApiError;
import com.example.javaProj.DTO.ArgumentError;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        ApiError apiError = new ApiError(ex.getMessage(), OffsetDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ArgumentError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ArgumentError argumentError = new ArgumentError(ex.getMessage());
        return new ResponseEntity<>(argumentError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ArgumentError> handleMissingParameterException(MissingServletRequestParameterException ex) {
        ArgumentError argumentError = new ArgumentError("Required parameter is missing: " + ex.getParameterName());
        return new ResponseEntity<>(argumentError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ArgumentError> handleMissingParameterException(EntityNotFoundException ex) {
        ArgumentError argumentError = new ArgumentError(ex.getMessage());
        return new ResponseEntity<>(argumentError, HttpStatus.BAD_REQUEST);
    }
}
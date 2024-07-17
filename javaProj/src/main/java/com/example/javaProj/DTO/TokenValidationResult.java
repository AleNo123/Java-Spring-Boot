package com.example.javaProj.DTO;

import com.example.javaProj.model.Token;
import com.example.javaProj.model.User;

public class TokenValidationResult {
    private boolean valid;
    private String message;
    private User user;

    public TokenValidationResult(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }
    public TokenValidationResult(boolean valid, String message, User user) {
        this.valid = valid;
        this.message = message;
        this.user = user;
    }
    public boolean isValid() {
        return valid;
    }
    public String getMessage() {
        return message;
    }
    public User getUser() {
        return user;
    }
    @Override
    public String toString() {
        return "TokenValidationResult{" +
                "valid=" + valid +
                ", message='" + message + '\'' +
                '}';
    }
}
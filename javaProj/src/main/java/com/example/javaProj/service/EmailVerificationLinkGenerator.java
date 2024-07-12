package com.example.javaProj.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailVerificationLinkGenerator {

    @Value("${app.base.url}")  // Значение базового URL вашего приложения
    private String baseUrl;

    public String generateEmailVerificationLink(String token) {
        return baseUrl + "/verify-email?token=" + token;
    }
}
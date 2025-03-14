package com.example.javaProj.exception;

import org.springframework.security.core.AuthenticationException;

public class EmailNotConfirmedException extends AuthenticationException {
    public EmailNotConfirmedException(String msg) {
        super(msg);
    }
}

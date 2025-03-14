package com.example.javaProj.exception;

import org.springframework.security.core.AuthenticationException;

public class BlockedUserException extends AuthenticationException {
    public BlockedUserException(String msg) {
        super(msg);
    }
}
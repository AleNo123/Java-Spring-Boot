package com.example.javaProj.security;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegistrationRequest {
    @NotNull
    @NotBlank
    @Size(min = 4, max = 25)
    private String nickname;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    @Email(message = "Invalid email format")
    @Column(name = "email_address")
    private String emailAddress;

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}

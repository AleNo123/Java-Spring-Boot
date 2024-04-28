package com.example.javaProj.DTO;

import com.example.javaProj.model.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {
    @NotNull
    @NotBlank
    @Size(min = 4, max = 25)
    private String nickname;
    @NotNull
    @NotBlank
    @Size(min = 3,max = 30)
    private String name;
    @NotNull
    @NotBlank
    @Size(min = 3,max = 30)
    private String surname;

    @NotNull
    @NotBlank
    @Email(message = "Invalid email format")
    @Column(name = "email_address")
    private String emailAddress;
    public User convertToUser(){
        User user = new User();
        user.setName(this.name);
        user.setEmailAddress(this.emailAddress);
        user.setNickname(this.nickname);
        user.setSurname(this.surname);
        return user;
    }
    public UserRequestDTO(String nickname, String name, String surname,String emailAddress) {
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.emailAddress = emailAddress;
    }
    @Override
    public String toString() {
        return "UserRequestDTO{" +
                "nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}

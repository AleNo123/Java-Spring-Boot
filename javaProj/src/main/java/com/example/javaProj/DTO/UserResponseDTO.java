package com.example.javaProj.DTO;

import com.example.javaProj.model.Course;
import com.example.javaProj.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class UserResponseDTO {

    private Long userId;
    @NotNull
    @Size(min = 4, max = 25)
    private String nickname;
    @NotNull
    @Size(min = 3,max = 30)
    private String name;
    @NotNull
    @Size(min = 3,max = 30)
    private String surname;
    @NotBlank
    private String pathToUserIcon;
    private Set<Course> courses;

    public UserResponseDTO(Long userId, String nickname, String name, String surname, String pathToUserIcon, Set<Course> courses) {
        this.userId = userId;
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.pathToUserIcon = pathToUserIcon;
        this.courses = courses;
    }

    public Long getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPathToUserIcon() {
        return pathToUserIcon;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public UserResponseDTO(User user){
        this.userId = user.getUserId();
        this.nickname =user.getNickname();
        this.name =user.getName();
        this.surname = user.getSurname();
        this.pathToUserIcon = user.getPathToUserIcon();
        this.courses = user.getCourses();
    }
    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pathToUserIcon='" + pathToUserIcon + '\'' +
                ", courses=" + courses +
                '}';
    }
}

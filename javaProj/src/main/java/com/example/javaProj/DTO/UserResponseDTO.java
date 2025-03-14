package com.example.javaProj.DTO;

import com.example.javaProj.View.CourseNameView;
import com.example.javaProj.View.UserView;
import com.example.javaProj.model.Course;
import com.example.javaProj.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
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
    @NotNull
    @NotBlank
    private String emailAddress;
    @NotBlank
    private String fileType;
    private Set<CourseNameView> courses;

//    public UserResponseDTO(Long userId, String nickname, String name, String surname, String pathToUserIcon,
//                           String fileType, Set<CourseNameView> courses) {
//        this.userId = userId;
//        this.nickname = nickname;
//        this.name = name;
//        this.fileType = fileType;
//        this.surname = surname;
//        this.pathToUserIcon = pathToUserIcon;
//        this.courses = courses;
//    }
    public UserResponseDTO(UserView userView) {
        this.userId = userView.getUserId();
        this.nickname = userView.getNickname();
        this.name = userView.getName();
        this.surname = userView.getSurname();
        this.pathToUserIcon = userView.getAvatar().getFilePath();
        this.fileType = userView.getAvatar().getFileType();
        this.courses = userView.getCourses();
        this.emailAddress = userView.getEmailAddress();
    }

//    public UserResponseDTO(User user){
//        this.userId = user.getUserId();
//        this.nickname =user.getNickname();
//        this.name =user.getName();
//        this.surname = user.getSurname();
//        this.pathToUserIcon = user.getAvatar().getFilePath();
//        this.pathToUserIcon = user.getAvatar().getFileType();
//        this.courses = user.getCourses().stream().map(course -> (CourseNameView) ()  -> course.getTitle()).collect(Collectors.toSet());
//    }
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

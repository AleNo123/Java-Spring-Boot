package com.example.javaProj.DTO;

import com.example.javaProj.model.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequestDTO {
    @NotNull
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 4, max = 25)
    private String nickname;
    @NotNull
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 8,max = 30)
    private String password;
    @NotNull
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 8,max = 30)
    private String passwordCopy;
    @NotNull
    @NotBlank(message = "Поле не должно быть пустым")
    @Email(message = "Invalid email format")
    @Column(name = "email_address")
    private String emailAddress;

    public User convertToUser(PasswordEncoder passwordEncoder){
        User user = new User();
        user.setName("");
        user.setEmailAddress(this.emailAddress);
        user.setNickname(this.nickname);
        user.setSurname("");
        user.setPassword(passwordEncoder.encode(this.password));
        return user;
    }
    public UserRegisterRequestDTO(String nickname,String emailAddress, String password) {
        this.nickname = nickname;
        this.password = password;
        this.emailAddress = emailAddress;
    }
    @AssertTrue(message = "Password must contain only ASCII characters")
    public boolean isPasswordValid() {
        return password.matches("\\p{ASCII}+");
    }
    @AssertTrue(message = "Passwords do not match")
    public boolean isPasswordsMatch() {
        return password.equals(passwordCopy);
    }

    @Override
    public String toString() {
        return "UserRequestDTO{" +
                "nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}

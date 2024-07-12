package com.example.javaProj.service;

import com.example.javaProj.DTO.UserRegisterRequestDTO;
import com.example.javaProj.DTO.UserResponseDTO;
import com.example.javaProj.View.UserView;
import com.example.javaProj.Enum.AccessRights;
import com.example.javaProj.exception.PasswordMismatchException;
import com.example.javaProj.model.File;
import com.example.javaProj.model.Token;
import com.example.javaProj.model.User;
import com.example.javaProj.repository.FileRepository;
import com.example.javaProj.repository.TokenRepository;
import com.example.javaProj.repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;
    @Autowired
    private FileService fileService;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    public UserResponseDTO getUserById(Long id){
//        UserResponseDTO responseDTO = repository.findById(id);
        UserView userView = repository.findUserViewByUserId(id).orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
        UserResponseDTO userResponseDTO = new UserResponseDTO(userView.getUserId(),userView.getNickname(),
                userView.getName(), userView.getSurname(), userView.getAvatar().getFilePath(),
                userView.getAvatar().getFileType(),userView.getCourses());

        return userResponseDTO;
    }
    public User createUser(UserRegisterRequestDTO requestDTO, PasswordEncoder passwordEncoder){
        if (repository.existsByNickname(requestDTO.getNickname())) {
            throw new DataIntegrityViolationException("User with the same nickname already exists");
        }
        if (repository.existsByEmailAddress(requestDTO.getEmailAddress())) {
            throw new DataIntegrityViolationException("User with the same email address already exists");
        }
        User user = requestDTO.convertToUser(passwordEncoder);
        user.setAccessRights(AccessRights.USER.name());
        user.setAchievements("");
        user.setContactLinks("");
        user.setAvatar(fileService.getDefaultAvatar());
        repository.save(user);
        return user;
    }
    public void deleteUser(User user){
        repository.delete(user);
    }

//    public void sendConfirmEmail(String receiver, User user) throws MessagingException {
//        String uuid = UUID.randomUUID().toString();
//        Token token = new Token(uuid,user);
//        emailService.sendVerificationEmail(receiver,uuid);
//        tokenRepository.save(token);
//    }
}

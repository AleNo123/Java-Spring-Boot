package com.example.javaProj.service;

import com.example.javaProj.DTO.TokenValidationResult;
import com.example.javaProj.DTO.UserRegisterRequestDTO;
import com.example.javaProj.DTO.UserResponseDTO;
import com.example.javaProj.View.UserView;
import com.example.javaProj.Enum.AccessRights;
import com.example.javaProj.model.User;
import com.example.javaProj.repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    @Autowired
    private FileService fileService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    public UserResponseDTO getUserById(Long id){
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

    public Optional<User> findByEmail(String email){
        return repository.findByEmailAddress(email);
    }

    public TokenValidationResult verifyUserByEmail(String token){
        TokenValidationResult result = emailService.verifyEmail(token);
        if(result.getUser()==null){
            return result;
        }else if (!result.getUser().getIsEmailConfirmed()) {
            if (result.isValid()) {
                result.getUser().setIsEmailConfirmed(true);
                repository.save(result.getUser());
            } else {
                if (result.getUser() != null) {
                    repository.delete(result.getUser());
                }
            }
        }
        return result;
    }
    public void sendVerificationEmail(String receiver, User user, String endpoint) throws MessagingException {
        emailService.sendVerificationEmail(receiver,user,endpoint);
    }
    public void setPassword(String password,User user){
        user.setPassword(passwordEncoder.encode(password));
        repository.save(user);
    }
}

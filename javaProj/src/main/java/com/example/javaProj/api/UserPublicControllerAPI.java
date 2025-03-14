package com.example.javaProj.api;

import com.example.javaProj.DTO.TokenValidationResult;
import com.example.javaProj.DTO.UserRegisterRequestDTO;
import com.example.javaProj.controller.UserController;
import com.example.javaProj.model.User;
import com.example.javaProj.response.ApiResponse;

import com.example.javaProj.service.EmailService;
import com.example.javaProj.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/public/user")
public class UserPublicControllerAPI {
    private final UserService service;
    private final PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(UserPublicControllerAPI.class);
    @Autowired
    public UserPublicControllerAPI(UserService service, PasswordEncoder passwordEncoder){
        this.service = service;
        this.passwordEncoder=passwordEncoder;
    }
    @Autowired
    private UserService userService; // Сервис для взаимодействия с пользователями

    @Autowired
    private EmailService emailService; // Сервис для отправки email

    @PostMapping("/forgotPassword")
    public ResponseEntity<ApiResponse> sendResetEmail(@RequestParam(name = "email") String email) {
        logger.info("email: " +email);
        Optional<User> userOptional = userService.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (!user.getIsEmailConfirmed()) {
                return ResponseEntity.badRequest().body(new ApiResponse(true, "This email is not available in the system"));
            }

            try {
                emailService.sendVerificationEmail(email,user, "user/resetPassword");
                return ResponseEntity.ok(new ApiResponse(false, "You have received a confirmation email"));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(true, e.getMessage()));
            }
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse(true, "This email is not available in the system"));
        }
    }
    @PostMapping("/resetPassword")
    public ResponseEntity<ApiResponse> resetPassword(@RequestParam(name = "password") String password,@RequestParam(name = "passwordCopy") String passwordCopy, @RequestParam(name = "token") String token) {


        if (!password.equals(passwordCopy)) {
            return ResponseEntity.badRequest().body(new ApiResponse(true, "Passwords do not match"));
        }

        TokenValidationResult result = userService.verifyUserByEmail(token);
        if (result.isValid()) {
            return ResponseEntity.ok(new ApiResponse(false, "Password changed successfully"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse(true, result.getMessage()));
        }
    }
    @PostMapping("/registration")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse> createUser(@Valid @ModelAttribute UserRegisterRequestDTO requestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (final ObjectError objectError : bindingResult.getAllErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(true, objectError.getDefaultMessage()));
            }
        }
        User user = null;
        try {
            user = service.createUser(requestDTO, passwordEncoder);
            service.sendVerificationEmail(user.getEmailAddress(), user, "verify-email");
        } catch (DataIntegrityViolationException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(true,exception.getMessage()));
        } catch (MessagingException e) {
            service.deleteUser(user);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(true,e.getMessage()));
        }
        return ResponseEntity.ok(new ApiResponse(false,"You have received a confirmation email"));
    }
    @GetMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestParam(value = "error", required = false) String error,
                                             @RequestParam(value = "message", required = false) String message,
                                             HttpSession session) {

        if (error != null) {
            String errorMessage = (String) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(true, errorMessage));
        }
        return ResponseEntity.ok(new ApiResponse(false, message));
    }
}

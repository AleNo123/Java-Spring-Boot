package com.example.javaProj.api;

import com.example.javaProj.DTO.UserDTO;
import com.example.javaProj.DTO.UserResponseDTO;
import com.example.javaProj.controller.UserController;
import com.example.javaProj.model.User;
import com.example.javaProj.response.ApiResponse;
import com.example.javaProj.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/private/user")
public class UserPrivateControllerAPI {
    private final UserService service;
    private final PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(UserPrivateControllerAPI.class);
    @Autowired
    public UserPrivateControllerAPI(UserService service, PasswordEncoder passwordEncoder){
        this.service = service;
        this.passwordEncoder=passwordEncoder;
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.logout();
        SecurityContextHolder.clearContext();

    }
    @GetMapping("/profile")
    public ResponseEntity<UserResponseDTO> getAuthorizedUser(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        UserResponseDTO userResponseDTO = service.getUserByNickname(username);
        return ResponseEntity.ok(userResponseDTO);
    }
    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@ModelAttribute UserDTO userDto,Authentication authentication) {
        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            System.out.println(userDto.toString());
            service.updateUser(userDto, userDetails.getUsername());
            return ResponseEntity.ok("The user has been successfully updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update the user");
        }
    }
}

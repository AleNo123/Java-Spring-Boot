package com.example.javaProj.controller;

import com.example.javaProj.DTO.UserRegisterRequestDTO;

import com.example.javaProj.DTO.UserResponseDTO;
import com.example.javaProj.View.UserView;
import com.example.javaProj.model.User;
import com.example.javaProj.security.RegistrationRequest;
import com.example.javaProj.service.EmailService;
import com.example.javaProj.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
@RequestMapping("/user")
@EnableWebMvc
public class UserController {
    private final UserService service;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    public UserController(UserService service, PasswordEncoder passwordEncoder){
        this.service = service;
        this.passwordEncoder=passwordEncoder;
    }

    @ResponseBody
    @GetMapping("")
    public String getUserById(Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()) {
            // Получаем информацию о текущем пользователе
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            // Возвращаем информацию о текущем пользователе
            return "Hello: " + username;
        } else {
            return "User not authorized";
        }
    }
    @GetMapping("/logout")
    public String logout(Authentication authentication){
        SecurityContextHolder.clearContext();
        return "redirect:/login?logout";
    }
    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id,Model model){
        UserResponseDTO userResponseDTO = service.getUserById(id);
        model.addAttribute("user", userResponseDTO);
        return "profile";
    }
    @GetMapping("/registration")
    public String registration(Model model){
        RegistrationRequest request = new RegistrationRequest();
        model.addAttribute(request);
        return "registrate";
    }
    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@Valid @ModelAttribute UserRegisterRequestDTO requestDTO, BindingResult bindingResult, Model model)  {
        if(bindingResult.hasErrors()){
            for (final ObjectError objectError : bindingResult.getAllErrors()) {
                System.out.println(objectError.getDefaultMessage());
                model.addAttribute("message",objectError.getDefaultMessage());
            }
            return "registrate";
        }
        User user = null;
        try {
            user = service.createUser(requestDTO, passwordEncoder);
            emailService.sendVerificationEmail(user.getEmailAddress(),user);
        } catch (DataIntegrityViolationException exception){
            model.addAttribute("message",exception.getMessage());
            return "registrate";
        } catch (MessagingException e) {
            model.addAttribute("message",e.getMessage());
            service.deleteUser(user);
            return "registrate";
        }
        model.addAttribute("message","You have received a confirmation email");
        return "registrate";
    }
    @ResponseBody
    @PutMapping("/{id}")
    public void changeUser(@Valid @RequestBody UserRegisterRequestDTO requestDTO, @Valid @PathVariable(name = "id") long id){

    }
//   /user/registration
}

//    @PutMapping("/{id}")
//
//    @DeleteMapping("/{id}")
//}

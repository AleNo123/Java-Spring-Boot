package com.example.javaProj.controller;

import com.example.javaProj.DTO.TokenValidationResult;
import com.example.javaProj.DTO.UserRegisterRequestDTO;

import com.example.javaProj.DTO.UserResponseDTO;
import com.example.javaProj.View.UserView;
import com.example.javaProj.model.User;
import com.example.javaProj.security.RegistrationRequest;
import com.example.javaProj.service.EmailService;
import com.example.javaProj.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
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
import java.util.Optional;

@Controller
@RequestMapping("/user")
@EnableWebMvc
public class UserController {
    private final UserService service;
    private final PasswordEncoder passwordEncoder;

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
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
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
            service.sendVerificationEmail(user.getEmailAddress(),user,"verify-email");
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
    @GetMapping("/forgotPassword")
    public String forgotPassword(Model model){

        return "forgotPassword";
    }
    @GetMapping(path="/forgotPassword", params = "token")
    public String createNewPassword(@RequestParam(name = "token") String token, Model model, HttpSession session){
        TokenValidationResult result = service.verifyUserByEmail(token);
        logger.info( result.toString());
        if (result.isValid()){
            model.addAttribute("isValid",true);
            session.setAttribute("user", result.getUser());
        } else {
            model.addAttribute("message",result.getMessage());
        }
        return "forgotPassword";
    }
    @PostMapping(path= "/forgotPassword",params = {"password","passwordCopy"})
    public String changePassword(@RequestParam(name = "password") String password,@RequestParam(name = "passwordCopy") String passwordCopy, HttpSession session, Model model){
        if (session.getAttribute("user")==null){
            model.addAttribute("message","Invalid session");
            model.addAttribute("error",true);
            return "forgotPassword";
        }
        if(password.equals(passwordCopy)){
            User user = (User) session.getAttribute("user");
            service.setPassword(password,user);
            model.addAttribute("message", "Password changed successfully");
            session.removeAttribute("user");
        } else {
            model.addAttribute("message", "Passwords do not match");
            return "forgotPassword";
        }
        return "redirect:/login";
    }
    @PostMapping(path="/forgotPassword",params = "email")
    public String SendEmailToChangePassword(@RequestParam(name = "email") String email,  Model model){
        Optional<User> user = service.findByEmail(email);
        if(user.isPresent()){
            if (!user.get().getIsEmailConfirmed()){
                model.addAttribute("message","This e-mail is not available in the system");
                return "forgotPassword";
            }
            try {
                service.sendVerificationEmail(email,user.get(),"user/forgotPassword");
            } catch (MessagingException e) {
                model.addAttribute("message", e.getMessage());
                return "forgotPassword";
            }
            model.addAttribute("message","You have received a confirmation email");
            model.addAttribute("isSend",true);
        } else {
            model.addAttribute("message","This e-mail is not available in the system");
        }
        return "forgotPassword";
    }
//   /user/registration
}

//    @PutMapping("/{id}")
//
//    @DeleteMapping("/{id}")
//}

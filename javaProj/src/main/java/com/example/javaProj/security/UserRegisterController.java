package com.example.javaProj.security;


import com.example.javaProj.controller.UserController;
import com.example.javaProj.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
public class UserRegisterController {

    private final UserService service;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    public UserRegisterController(UserService service){
        this.service = service;
    }


    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "message", required = false) String message,
                        Model model, HttpSession session) {
        if (error != null) {
            model.addAttribute("error", true);
            model.addAttribute("message", (String) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION));
        }
        return "login";
    }
//    @GetMapping("user/registration")
//    public String registration(Model model){
//        RegistrationRequest request = new RegistrationRequest();
//        model.addAttribute(request);
//        return "registrate";
//    }
}
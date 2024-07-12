package com.example.javaProj.controller;

import com.example.javaProj.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class EmailController {
    @Autowired
    private EmailService emailService;
    @GetMapping("verify-email")
    public String verifyEmail(@RequestParam(name = "token") String token, Model model){
        model.addAttribute("message",emailService.verifyEmail(token));
        return "verify-email";
    }
}

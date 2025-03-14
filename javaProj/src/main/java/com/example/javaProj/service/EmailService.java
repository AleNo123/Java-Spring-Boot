package com.example.javaProj.service;

import com.example.javaProj.DTO.TokenValidationResult;
import com.example.javaProj.controller.UserController;
import com.example.javaProj.model.Token;
import com.example.javaProj.model.User;
import com.example.javaProj.repository.TokenRepository;
import com.example.javaProj.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String baseEmail;

    private final Logger logger = LoggerFactory.getLogger(EmailService.class);
    @Autowired
    private TokenRepository tokenRepository;

    @Value("${app.base.url}")  // Значение базового URL вашего приложения
    private String baseUrl;

    public String generateEmailVerificationLink(String token,String endpoint) {
        return baseUrl +"/"+ endpoint+ "?token=" + token;
    }
    public void sendVerificationEmail(String receiver, User user, String endpoint) throws MessagingException {
        String uuid = UUID.randomUUID().toString();
        Optional<Token> token = tokenRepository.findByUser(user);
        if (token.isPresent()){
            token.get().setToken(uuid);
            token.get().setDateCreation(OffsetDateTime.now());
            tokenRepository.save(token.get());
        } else {
            Token verificationToken = new Token(uuid,user);
            tokenRepository.save(verificationToken);
        }
        String verificationLink = generateEmailVerificationLink(uuid,endpoint);
        String messageText = "Пожалуйста, перейдите по ссылке для подтверждения вашего email: " + verificationLink;
        sendEmail(messageText,receiver,"Подтверждение почты");
    }
    public void sendEmail(String messageText, String receiver, String title) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(baseEmail);
        helper.setTo(receiver);
        helper.setSubject(title);
        helper.setText(messageText);
        mailSender.send(message);
        logger.info("message was send to " + receiver);
    }
//    public String verifyEmail(String token){
//        Optional<Token> optionalToken = tokenRepository.getByToken(token);
//        if(optionalToken.isPresent()){
//            Token token1 = optionalToken.get();
//            Duration duration = Duration.between(token1.getDateCreation(),OffsetDateTime.now());
//            if(duration.toMinutes()>30){
//                User user = (token1.getUser());
//                tokenRepository.delete(token1);
//                userService.deleteUser(user);
//                return "The link is outdated";
//            }
//            User user = token1.getUser();
//            user.setIsEmailConfirmed(true);
//            userRepository.save(user);  //Надо бы через сервис обновлять но пока такой функции нет ... (потом исправлю)
//            tokenRepository.delete(token1);
//            return "All is ok";
//        }
//        return "Unsupported token";
//    }
    public TokenValidationResult verifyEmail(String token){
        Optional<Token> optionalToken = tokenRepository.getByToken(token);
        if(optionalToken.isPresent()) {
            Token token1 = optionalToken.get();
            Duration duration = Duration.between(token1.getDateCreation(), OffsetDateTime.now());
            if (duration.toMinutes() > 30) {
                User user = (token1.getUser());
                tokenRepository.delete(token1);
                return new TokenValidationResult(false, "The link is outdated",user);
            }
            User user = token1.getUser();
            tokenRepository.delete(token1);
            return new TokenValidationResult(true, "All is ok",user);
        }
        return new TokenValidationResult(false, "Unsupported token");

    }
}
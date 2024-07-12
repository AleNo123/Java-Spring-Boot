package com.example.javaProj.security;

import com.example.javaProj.Enum.AccessRights;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

//@Service
//public class UserRegisterService {
//    private final UserDetailsManager userDetailsManager;
//    private final PasswordEncoder passwordEncoder;
//
//    public UserRegisterService(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
//        this.userDetailsManager = userDetailsManager;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public void registerUser(String username, String password, String email ) {
//        String encodedPassword = passwordEncoder.encode(password);
//        userDetailsManager.createUser(User.withUsername(username)
//                .password(encodedPassword)
//                .roles(AccessRights.USER.name())
//                .build());
//    }
//}

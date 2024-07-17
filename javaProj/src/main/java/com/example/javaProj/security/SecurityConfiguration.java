package com.example.javaProj.security;

import com.example.javaProj.model.User;
import com.example.javaProj.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;;import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {

    @Autowired
    private UserDetailsService userDetailService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public void authConfigure(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin/**").hasRole("ADMIN") //swagger-ui
                        .requestMatchers("/user/registration/**").permitAll()
                                .requestMatchers("/user/registration").permitAll()
                        .requestMatchers("/registration").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/verify-email").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/static/**").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/logout").permitAll()
                                .requestMatchers("/user/forgotPassword").permitAll()
                                .requestMatchers("/user/forgotPassword/**").permitAll()
//                        .requestMatchers("/v2/api-docs", "/swagger-resources/**", "/webjars/**"
//                                                   ,"/configuration/ui","/swagger-resources/**", "/configuration/security"
//                                                    ,"/swagger-ui.html", "/webjars/**").permitAll()
                        //web.ignoring().antMatchers("/v2/api-docs",
                        //                                   "/configuration/ui",
                        //                                   "/swagger-resources/**",
                        //                                   "/configuration/security",
                        //                                   "/swagger-ui.html",
                        //                                   "/webjars/**");
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage("/login")
                        .failureHandler(authenticationFailureHandler())
                        .defaultSuccessUrl("/user")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/login")
                        .logoutUrl("/user/logout")
                        ); //
        return http.build();
    }
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                String errorMessage=exception.getMessage();
                request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
                getRedirectStrategy().sendRedirect(request, response, "/login?error");
            }
        };
    }
//    loginPage("/account/login")
//                .failureUrl("/account/login?error=true")
//                .defaultSuccessUrl("/account/admin/")
//                .usernameParameter("email")
//                .passwordParameter("password")
}

//admin admin123
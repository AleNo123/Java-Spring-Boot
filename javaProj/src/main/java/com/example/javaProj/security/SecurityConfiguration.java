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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;;import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())

                .csrf((csrf) -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(new SpaCsrfTokenRequestHandler())
                )
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin/**").hasRole("ADMIN") //swagger-ui
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("api/public/**").permitAll()
                        .requestMatchers("api/private/**").authenticated()
                        .anyRequest().authenticated()
                )
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .authenticationEntryPoint((request, response, authException) -> {
                                    System.out.println("A lot of hyi");
                                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                    response.setContentType("application/json");
                                    response.getWriter().write("{\"error\": \"Unauthorized\"}");
                                })
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage("/api/public/user/login")
                        .failureHandler(authenticationFailureHandler())
                        .defaultSuccessUrl("/api/private/user/profile")
                        .permitAll()
                )
                .logout((logout) -> logout
//                        .logoutSuccessUrl("/login")
                        .logoutUrl("/api/private/user/logout")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                        ); //
        return http.build();
    }
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                String errorMessage=exception.getMessage();
                logger.info(errorMessage);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"message\": \"" + errorMessage + "\"}");
//                request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
//                getRedirectStrategy().sendRedirect(request, response, "/api/public/user/login?error");
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
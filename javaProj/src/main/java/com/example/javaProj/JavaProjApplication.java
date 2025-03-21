package com.example.javaProj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages = {"com.example.javaProj"})
public class JavaProjApplication {
	public static void main(String[] args) {
		SpringApplication.run(JavaProjApplication.class, args);
	}

}

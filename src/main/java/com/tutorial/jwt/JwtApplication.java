package com.tutorial.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}

}

/*
 * Enable Web SpringBoot Security
 * by adding security dependency (spring-boot-starter-security).
 * This allows to login before call any API till logout.
 * 
 * User - User
 * Password - Password will be generated by system You will see in console.
 * This password will be only generated when you don't define it explicitly in application file.
 * 
 * When you try to hit any API without authenticated automatic inbuilt login API called
 * Login URL -> http://localhost:8080/login
 * Logout URL -> http://localhost:8080/logout
 * 
 * If you want to give your user and password you can provide it from application.properties file.
 * You can check there-
 * 
 * Till here we did not integrate JWT.
 */

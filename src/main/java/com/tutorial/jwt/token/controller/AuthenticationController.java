package com.tutorial.jwt.token.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.jwt.token.dto.UserEntity;
import com.tutorial.jwt.token.helper.JwtHelper;
import com.tutorial.jwt.token.service.AuthenticationService;
import com.tutorial.jwt.token.dto.LoginResponse;
import com.tutorial.jwt.token.dto.LoginUserDto;
import com.tutorial.jwt.token.dto.RegisterUserDto;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
	
	@Autowired
    private JwtHelper jwtService;
    
	@Autowired
    private AuthenticationService authenticationService;

   
    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody RegisterUserDto registerUserDto) {
    	UserEntity registeredUser = authenticationService.signup(registerUserDto);
    	String message = null;
    	if(registeredUser == null) {
    		message = "Already Exist";
    	} else 
    		message = "Registered";
        return new ResponseEntity<String>(message,HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
    	UserEntity authenticatedUser = authenticationService.authenticate(loginUserDto);
    	
    	//If authentication success generate a token.
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}

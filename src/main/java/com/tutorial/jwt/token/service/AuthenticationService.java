package com.tutorial.jwt.token.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tutorial.jwt.token.dto.LoginUserDto;
import com.tutorial.jwt.token.dto.RegisterUserDto;
import com.tutorial.jwt.token.dto.UserEntity;
import com.tutorial.jwt.token.repo.UserRepository;

/* Service for signup and login.
 * 
 */

@Service
public class AuthenticationService {
	
	@Autowired
    private UserRepository userRepository;
    
	@Autowired
    private PasswordEncoder passwordEncoder;
    
	@Autowired
    private AuthenticationManager authenticationManager;


	//SignUp
    public UserEntity signup(RegisterUserDto input) {
    	//Check if user already exist
    	try {
    		if(userRepository.findByEmail(input.getEmail()).get() != null) {
        		return null;
        	}
    	} catch(NoSuchElementException e) {
    		
    	}
    	
    	
    	UserEntity user = new UserEntity();
                user.setFullName(input.getFullName());
                user.setEmail(input.getEmail());
                user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    //Login
    public UserEntity authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}

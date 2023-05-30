package com.springProject.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springProject.dto.RegisterRequest;
import com.springProject.model.NotificationEmail;
import com.springProject.model.User;
import com.springProject.model.VerificationToken;
import com.springProject.repository.UserRepository;
import com.springProject.repository.verificationTokenRepository;

import jakarta.transaction.Transactional;

@Service 

public class AuthService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired 
	private UserRepository userRepository;
	@Autowired 
	private verificationTokenRepository tokenrepository;
	@Autowired 
	private mailService mailservice;
	@Transactional
	public void signup (RegisterRequest registerRequest) {
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        /*user.setCreated(Instant.now());
        user.setEnabled(false);*/
        userRepository.save(user);
        String Token = generateVerificationtoken(user);
        mailservice.sendMail(new NotificationEmail("Please Activate your Account",
        	    user.getEmail(),"Thank you for signing up to Spring Reddit"
        		+"please click on the below url to activate your account : "
        		+ "http://localhost:8080/api/auth/accountVerification" + Token));
    
	}
	private String generateVerificationtoken (User user) {
		String token  = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        tokenrepository.save(verificationToken);
        return token;
	}
}

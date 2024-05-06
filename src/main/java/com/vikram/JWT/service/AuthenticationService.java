package com.vikram.JWT.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vikram.JWT.model.AuthenticationResponse;
import com.vikram.JWT.model.User;
import com.vikram.JWT.repository.UserRepository;

@Service
public class AuthenticationService {
	
	private final UserRepository repo; 
	private final PasswordEncoder passwordEncoder; 
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager; 
	
	public AuthenticationService(UserRepository repo, PasswordEncoder passwordEncoder, JwtService jwtService,
			AuthenticationManager authenticationManager) {
		this.repo = repo;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}
	public AuthenticationResponse register(User request)
	{
		User user=new User(); 
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setUserName(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(request.getRole());
		user=repo.save(user); 
		String token=jwtService.generateToken(user); 
		return new AuthenticationResponse(token); 
		
	}
	public AuthenticationResponse authenticate(User request)
	{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.getUsername(), 
				request.getPassword()));
		User user=repo.findByUsername(request.getUsername()).orElseThrow(); 
		String token=jwtService.generateToken(user); 
		return new AuthenticationResponse(token); 
		
	}

}

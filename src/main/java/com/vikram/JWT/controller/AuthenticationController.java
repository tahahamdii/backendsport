package com.vikram.JWT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vikram.JWT.model.AuthenticationResponse;
import com.vikram.JWT.model.User;
import com.vikram.JWT.service.AuthenticationService;
import com.vikram.JWT.service.UserDetailsServiceImp;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthenticationController {

	private AuthenticationService authService;
	private  UserDetailsServiceImp usdi; 

	
	public AuthenticationController(AuthenticationService authService, UserDetailsServiceImp usdi) {
		super();
		this.authService = authService;
		this.usdi = usdi;
	}
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {

		return ResponseEntity.ok(authService.register(request));

	}
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody User request) {

		return ResponseEntity.ok(authService.authenticate(request));

	}
	@DeleteMapping("/delete/{id}")
	   public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) throws Exception
	   {
		usdi.deleteById(id);  
		return ResponseEntity.ok("User with id "+id+ "Deleted Successfully");
		   
	   }
    
}

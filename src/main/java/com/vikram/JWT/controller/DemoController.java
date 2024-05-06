package com.vikram.JWT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vikram.JWT.service.UserDetailsServiceImp;


@RestController
public class DemoController {
	
	private UserDetailsServiceImp usdi; 
	
	@GetMapping("/demo")
	public ResponseEntity<String> demo() {
		System.out.println("Hello from secured URL");
		return ResponseEntity.ok("Hello from Secured URL!!!"); 
	}
	@GetMapping("/admin_only")
	public ResponseEntity<String> adminOnly()
	{
		return ResponseEntity.ok("Hello from admin only url"); 
	}
	
   
	
	
	
}

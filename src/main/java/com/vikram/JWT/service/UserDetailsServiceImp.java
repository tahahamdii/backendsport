package com.vikram.JWT.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.vikram.JWT.model.User;
import com.vikram.JWT.repository.UserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
@Autowired
	private final UserRepository repository;

	public UserDetailsServiceImp(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("USer not found"));
	}

	public void deleteById(Integer id) throws Exception
	{
		Optional<User> user=repository.findById(id); 
		if(user.isPresent())
		{
		repository.deleteById(id);
	    }
		else
		{
			throw new Exception("User not found with id : "+id ); 
		}
	

}
}

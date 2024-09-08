package com.userService.UserService.service;


import com.userService.UserService.entity.User;

import com.userService.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userRepo.findByUsername(username)
	            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
	        return user;

	        //return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	    }
	 
//	 private Collection<? extends GrantedAuthority> getAuthority(User user) {
//	        return Arrays.stream(user.getRoles().split(","))
//	                     .map(SimpleGrantedAuthority::new)
//	                     .collect(Collectors.toList());
//	    }

}

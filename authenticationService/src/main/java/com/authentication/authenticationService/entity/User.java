package com.authentication.authenticationService.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@NotBlank(message="name can't be blank")
	private String username;
	
	@NotNull
	private String password;
		
	@NotNull
	@Email(message="Email should be valid")
	@NotBlank(message="email can't be blank")
	private String email;
	
	@NotNull
	@NotBlank(message="first name can't be blank")
	private String firstName;
	
	
	private String lastName;
	
	
	private String phone;
	private String address;
	
	private String roles;

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return null;
//	}

		@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
			return Arrays.stream(this.getRoles().split(","))
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
	}

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities(User user) {
//		return Arrays.stream(user.getRoles().split(","))
//				.map(SimpleGrantedAuthority::new)
//				.collect(Collectors.toList());
//	}

	

}

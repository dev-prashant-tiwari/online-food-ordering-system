package com.authentication.authenticationService.config;




import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.authentication.authenticationService.service.CustomerUserDetailsService;


@Configuration
@EnableWebSecurity
public class MyConfig {
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails userDetails1 = User.builder().username("amit").password(passwordEncoder().encode("root")).roles("ADMIN").build();
//		UserDetails userDetails2 = User.builder().username("rahul").password(passwordEncoder().encode("root")).roles("ADMIN").build();
//		UserDetails userDetails3 = User.builder().username("prashant").password(passwordEncoder().encode("root")).roles("ADMIN").build();
//		return new InMemoryUserDetailsManager(userDetails1, userDetails2, userDetails3);
//	}

//	@Bean
//	public OpenAPI customOpenAPI() {
//		return new OpenAPI()
//				.info(new Info().title("API Documentation")
//						.version("1.0")
//						.description("Documentation of API endpoints"))
//				.addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
//				.components(new io.swagger.v3.oas.models.Components()
//						.addSecuritySchemes("bearerAuth", new SecurityScheme()
//								.name("bearerAuth")
//								.type(SecurityScheme.Type.HTTP)
//								.scheme("bearer")
//								.bearerFormat("JWT")));
//	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authencationManager(AuthenticationConfiguration builder) throws Exception {
		
		return builder.getAuthenticationManager();
		
	}
	

}

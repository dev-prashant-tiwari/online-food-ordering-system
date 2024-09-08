package com.userService.UserService.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtHelper jwtHelper;
	
//	@Autowired
//	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestHeader = request.getHeader("Authorization");
		//it will be like "Bearer_ghjsdfkjk12323"
		log.info("requestHeader is "+requestHeader);
		
		String username = null;
		String token = null;
		
		if(requestHeader != null && requestHeader.startsWith("Bearer")) {
			token = requestHeader.substring(7);
			
			try {
				username = jwtHelper.getUserNameFromToken(token);
			}
			catch(IllegalArgumentException e) {
				log.warn("Illegal arguments while fetching the username !! ");
				e.printStackTrace();
				
			}
			catch(ExpiredJwtException e) {
				log.warn("toke is expired !!");
				e.printStackTrace();
				
			}
			catch (MalformedJwtException e) {
				log.warn("Token has been changed, invalid token !! ");
				e.printStackTrace();
				
			}
			catch(Exception e) {
				log.warn("some exception occurred while fetching the username");
				e.printStackTrace();
				
			}
		}
		else {
			log.info("Invalid Header value !!");
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			//fetch user details from usernam
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			Boolean validateToken = jwtHelper.validateToken(token, userDetails);
			
			if(validateToken) {
				//set the authentication
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			}
			else {
				log.info("Validation fails !!");
			}
			
			
		}
		
		filterChain.doFilter(request, response);
		
	}

}

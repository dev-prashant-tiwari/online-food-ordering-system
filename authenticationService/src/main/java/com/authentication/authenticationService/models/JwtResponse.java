package com.authentication.authenticationService.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {
	private String jwtToken;
	private String username;

}

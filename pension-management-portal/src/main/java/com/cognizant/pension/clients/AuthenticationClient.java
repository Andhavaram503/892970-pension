package com.cognizant.pension.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cognizant.pension.model.AuthenticationRequest;

@FeignClient(name="authorization-service",url="http://localhost:9090/authentication")
public interface AuthenticationClient {
	
	@PostMapping("/auth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest);

	@PostMapping("/validate")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token) ;

}

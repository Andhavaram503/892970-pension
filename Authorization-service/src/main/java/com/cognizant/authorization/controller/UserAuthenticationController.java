package com.cognizant.authorization.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cognizant.authorization.exceptions.UserExistsWithTheGivenCredentialsException;
import com.cognizant.authorization.model.AuthenticationRequest;
import com.cognizant.authorization.model.AuthenticationResponse;
import com.cognizant.authorization.model.User;
import com.cognizant.authorization.service.UserDetailsServices;
import com.cognizant.authorization.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/authentication")
@Slf4j
public class UserAuthenticationController {

	@Autowired
	private UserDetailsServices myUserDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/add-user")
	@ResponseStatus(code = HttpStatus.OK)
	//@ModelAttribute("user")
	public void addUser(@Valid @RequestBody User user) throws UserExistsWithTheGivenCredentialsException {
		log.info("User details while registering{} --->UserAuthenticationController", user);
		user.setRoles("ROLE_ADMIN");
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(false);
		log.info("user Details Updated --->  UserAuthenticationController");
		myUserDetailsService.addUser(user);
	}

	@PostMapping("/auth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {

		log.info("Creating Jwt Token for Authentication --->  UserAuthenticationController");
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUserName(), authenticationRequest.getPassword());
		authenticationManager.authenticate(token);
		log.info("User Authentication Completed --->  UserAuthenticationController");
		UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		String jwt = jwtUtil.generateToken(userDetails);
		log.info("Jwt Token generated for authentication --->  UserAuthenticationController");
		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}

	@PostMapping("/validate")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token) {
		log.info("===========>>token=================>>>" + token);
		UserDetails userDetails = myUserDetailsService.loadUserByUsername(jwtUtil.extractUsername(token));
		log.info(" getting User Details from context  to check the validity of token ");
		jwtUtil.validateToken(token, userDetails);
		log.info("Jwt Token validation successfull !!!");
		return new ResponseEntity<String>(HttpStatus.OK);

	}

}

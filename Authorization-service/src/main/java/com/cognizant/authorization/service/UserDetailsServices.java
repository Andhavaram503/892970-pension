package com.cognizant.authorization.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.cognizant.authorization.exceptions.UserExistsWithTheGivenCredentialsException;
import com.cognizant.authorization.model.User;

public interface UserDetailsServices extends UserDetailsService {

	String addUser(User user) throws UserExistsWithTheGivenCredentialsException;

}
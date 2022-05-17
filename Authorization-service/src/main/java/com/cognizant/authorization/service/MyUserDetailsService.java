package com.cognizant.authorization.service;
//import java.util.logging.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.authorization.exceptions.UserExistsWithTheGivenCredentialsException;
import com.cognizant.authorization.model.User;
import com.cognizant.authorization.repository.UserRepository;
import com.cognizant.authorization.user.UserPrincipal;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsServices {
	//Logger log=Logger.getLogger(MyUserDetailsService.class.getName());

	@Autowired
	private UserRepository userRepository; 
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{		
		log.info("inside loadUserByUsername method --> MyUserDetailsService");
		User user=userRepository.findByUserName(username);					
		log.info("User found with the Given User Name --> MyUserDetailsService");
		return new UserPrincipal(user);
	}

	@Override
	public String addUser( User user) throws UserExistsWithTheGivenCredentialsException {
		log.info("Inside AddingUser Method ----> MyUserDetailsService");
		if(userRepository.findByUserName(user.getUserName()) == null)	
		{	
			log.info("Encrypting the password of user ----> MyUserDetailsService");
		user.setPassword( new BCryptPasswordEncoder (15).encode(user.getPassword()));
		userRepository.save(user);	
		log.info("user registration is successful !!! ----> MyUserDetailsService");
		return "Registration Successfull!!!";
		}
		log.error("User Already Exists So Exception was raised !!! --->  MyUserDetailsService");
		throw new UserExistsWithTheGivenCredentialsException("User Exists please provide new Credentials !!!");
	}

}

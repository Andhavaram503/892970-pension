package com.cognizant.authorization.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;


@SuppressWarnings("serial")
@Slf4j
@ResponseStatus(code = HttpStatus.CONFLICT)
public class UserExistsWithTheGivenCredentialsException extends Exception {


	public UserExistsWithTheGivenCredentialsException(String message)
	{
		super(message);
		log.info("UserExistsWithTheGivenCredentials exception was raised !!!!");
	}
	
}

package com.cognizant.authorization.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class MalformedJwtException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MalformedJwtException() {
		log.error("MalformedJwtException was raised ");
	}	
	
}

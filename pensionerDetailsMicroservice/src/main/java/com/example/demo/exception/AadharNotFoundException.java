package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AadharNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AadharNotFoundException(String msg) {
		super(msg);
	}

}

package com.cognizant.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class AuthorizationServiceApplication {

	public static void main(String[] args) {
		log.info("AuthorizationServiceApplication starting");
		SpringApplication.run(AuthorizationServiceApplication.class, args);
		log.info("AuthorizationServiceApplication started");
	}

}

package com.cognizant.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@SpringBootApplication
@EnableSwagger2
public class AuthorizationServiceApplication {

	public static void main(String[] args) {
		log.info("AuthorizationServiceApplication starting");
		SpringApplication.run(AuthorizationServiceApplication.class, args);
		log.info("AuthorizationServiceApplication started");
	}

}

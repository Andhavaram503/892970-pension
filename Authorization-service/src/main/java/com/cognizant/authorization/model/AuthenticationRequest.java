package com.cognizant.authorization.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthenticationRequest {
	
	@NotNull(message = "Cannot be null")
	@NotBlank(message = "Cannot be Blank")
	@NotEmpty(message = "Cannot be Empty" )
	private String userName;	
	@NotNull(message = "Cannot be null")
	@NotBlank(message = "Cannot be Blank")
	@NotEmpty(message = "Cannot be Empty" )
	private String password;

}

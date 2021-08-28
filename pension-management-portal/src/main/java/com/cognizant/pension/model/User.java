package com.cognizant.pension.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	
	
	
	@NotNull(message = "Cannot be null")
	@NotBlank(message = "Cannot be Blank")
	@NotEmpty(message = "Cannot be Empty" )
	private String userName;
	@NotNull(message = "Cannot be null")
	@NotBlank(message = "Cannot be Blank")
	@NotEmpty(message = "Cannot be Empty" )
	private String password;
	private String roles;	
	private boolean isAccountNonExpired;	
	private boolean isAccountNonLocked;	
	private boolean isCredentialsNonExpired;	
	private boolean isEnabled;
	
	

}

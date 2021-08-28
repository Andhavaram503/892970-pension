package com.cognizant.authorization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Transient
public class User {
	
	
	
	@Id
	@Column( nullable = false)
	@NotNull(message = "Cannot be null")
	@NotBlank(message = "Cannot be Blank")
	@NotEmpty(message = "Cannot be Empty" )
	private String userName;
	@Column( nullable = false)
	@NotNull(message = "Cannot be null")
	@NotBlank(message = "Cannot be Blank")
	@NotEmpty(message = "Cannot be Empty" )
	private String password;
	@Column(columnDefinition = "varchar(255)  default 'ROLE_USER'")
	private String roles;	
	private boolean isAccountNonExpired;	
	private boolean isAccountNonLocked;	
	private boolean isCredentialsNonExpired;	
	private boolean isEnabled;
	
	

}

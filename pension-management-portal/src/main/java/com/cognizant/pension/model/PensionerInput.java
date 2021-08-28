package com.cognizant.pension.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PensionerInput {	
	private String name;
	@Setter
	private Date dateOfBirth;
	private String pan;
	private String aadhar;
	private String pensionType;
}

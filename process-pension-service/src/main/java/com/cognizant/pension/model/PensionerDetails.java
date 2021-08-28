package com.cognizant.pension.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PensionerDetails {
	
	private String name;
	private Date dateOfBirth;
	private String pan;
	private double salaryEarned;
	private double allowances;
	private String pensionType;
	private Bank bank;

}

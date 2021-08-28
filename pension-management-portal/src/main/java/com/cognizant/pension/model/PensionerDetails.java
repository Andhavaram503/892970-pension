package com.cognizant.pension.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PensionerDetails {

	private String name;

	private Date dateOfBirth;

	private String pan;

	private double salaryEarned;

	private double allowances;

	private String pensionType;

	private Bank bank;

	public void setDateOfBirth(Date dateOfBirth) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			    Date d1= formatter.parse(formatter.format(dateOfBirth));
		this.dateOfBirth = d1;
		
	}
	
	

}

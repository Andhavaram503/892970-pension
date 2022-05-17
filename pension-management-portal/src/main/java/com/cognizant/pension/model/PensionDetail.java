package com.cognizant.pension.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class PensionDetail {

	private String name;
	private Date dateOfBirth;
	private String pan;
	private String pensionType;
	private double pensionAmount;

}
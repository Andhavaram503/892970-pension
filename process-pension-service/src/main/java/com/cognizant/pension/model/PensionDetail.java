package com.cognizant.pension.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class PensionDetail {

	private String name;
	private Date dateOfBirth;
	private String pan;
	private String pensionType;
	private double pensionAmount;
	
}

package com.cts.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class PensionerDetails {
	private String name;
	private String dateOfBirth;
	private String pan;
	private double salaryEarned;
	private double allowances;
	private boolean isSelfPension;
	private boolean isFamilyPension;
	private Bank bank;
}

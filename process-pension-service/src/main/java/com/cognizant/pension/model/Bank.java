package com.cognizant.pension.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Bank {
	private String bankName;
	private long accountNumber;
	@Getter
	private String bankType;
}

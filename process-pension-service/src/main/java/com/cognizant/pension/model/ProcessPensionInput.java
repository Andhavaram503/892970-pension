package com.cognizant.pension.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProcessPensionInput {

	private String aadharNumber;
	private Double pensionAmount;
	private String bankType;

}

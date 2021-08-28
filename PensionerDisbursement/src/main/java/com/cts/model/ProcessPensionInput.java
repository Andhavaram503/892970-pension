package com.cts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessPensionInput {
	
	private long AadharNumber;
	private double PensionAmount;
	private String bankType;
	

}

package com.cts.Dao;

import org.springframework.stereotype.Component;

import com.cts.model.Bank;
import com.cts.model.ProcessPensionResponse;

@Component
public class PensionDisbursementDao implements PensionDisbursementDaoInterface {

	public ProcessPensionResponse getResponse(Bank bank, String serviceCharge) {
		ProcessPensionResponse p = new ProcessPensionResponse();
		String x = bank.getBankName().equalsIgnoreCase("public") ? "public" : "private";

		if (x.equalsIgnoreCase(serviceCharge)) {
			p.setStatus_code(10);
		} else {
			p.setStatus_code(21);
		}
		return p;
	}

}

package com.cts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.Dao.PensionDisbursementDao;
import com.cts.model.Bank;
import com.cts.model.ProcessPensionResponse;

@Service
public class PensionerDisbursementService implements PensionerDisbursementServiceInterface {
	@Autowired
	private PensionDisbursementDao pdd;
	
	@Override
	public ProcessPensionResponse getResponse(Bank bank,String serviceCharge) {
		return pdd.getResponse(bank, serviceCharge);
	}
	

}

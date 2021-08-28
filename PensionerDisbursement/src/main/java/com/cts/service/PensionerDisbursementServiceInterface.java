package com.cts.service;

import com.cts.model.Bank;
import com.cts.model.ProcessPensionResponse;

public interface PensionerDisbursementServiceInterface {

	ProcessPensionResponse getResponse(Bank bank, String bankType);


}
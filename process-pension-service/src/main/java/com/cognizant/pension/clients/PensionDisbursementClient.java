package com.cognizant.pension.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognizant.pension.model.ProcessPensionInput;
import com.cognizant.pension.model.ProcessPensionResponse;

@FeignClient(name="PensionerDisbursement", url="http://localhost:8888")
public interface PensionDisbursementClient {

	@PostMapping("/DisbursePension")
	public ProcessPensionResponse getResponse(@RequestBody ProcessPensionInput processPensionInput) throws Exception ;
	
	
}

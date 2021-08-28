package com.cognizant.pension.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognizant.pension.model.PensionDetail;
import com.cognizant.pension.model.PensionerInput;
import com.cognizant.pension.model.ProcessPensionInput;
import com.cognizant.pension.model.ProcessPensionResponse;

@FeignClient(name = "process-pension-service", url = "http://localhost:8081")
public interface ProcessPensionClient {

	@PostMapping("/PensionDetail")
	public PensionDetail getPensionDetails(PensionerInput pensionerInput) ;

	@PostMapping("/process-pension")
	public ProcessPensionResponse getProcessPension(@RequestBody ProcessPensionInput processPensionInput)
			throws Exception;

}

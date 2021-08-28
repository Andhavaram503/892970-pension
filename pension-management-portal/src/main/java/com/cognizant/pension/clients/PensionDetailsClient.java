package com.cognizant.pension.clients;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.pension.model.PensionerDetails;

@FeignClient(name="pensionerDetailsService",url="localhost:1111")
public interface PensionDetailsClient {
	
	@GetMapping("/details/{aadharNumber}")
	public PensionerDetails getDetails(@PathVariable long aadharNumber);
	
	@GetMapping("/pensions")
	public Map<Long, PensionerDetails> getPensioners();
}

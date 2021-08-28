package com.cognizant.pension.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.pension.model.PensionerDetails;

@FeignClient(name="pensionerDetailsService",url="http://localhost:1111")
public interface PensionerDetailsClient {
	
	
	@GetMapping("/details/{aadharNumber}")
	public PensionerDetails getDetails(@PathVariable String aadharNumber) throws Exception;

}

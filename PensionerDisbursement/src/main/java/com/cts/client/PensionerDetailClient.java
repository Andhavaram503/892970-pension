package com.cts.client;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.exception.AadharNotFoundException;
import com.cts.model.PensionerDetails;

@FeignClient(name="pensionerDetailsService",url="http://localhost:1111")
public interface PensionerDetailClient {
	@GetMapping("/details/{aadharNumber}")
	public PensionerDetails getDetails(@PathVariable long aadharNumber) throws NumberFormatException, IOException, ParseException, AadharNotFoundException;

}

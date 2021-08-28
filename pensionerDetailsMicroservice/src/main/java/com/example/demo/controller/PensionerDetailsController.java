package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PensionerDetails;
import com.example.demo.service.PensionerDetailsServiceInterface;

@RestController
public class PensionerDetailsController {

	@Autowired
	private PensionerDetailsServiceInterface pensionerDetailsService;

	@GetMapping("/details/{aadharNumber}")
	public PensionerDetails getDetails(@PathVariable long aadharNumber) {
		return pensionerDetailsService.getPensionerDetails(aadharNumber);
	}

	@GetMapping("/pensions")
	public Map<Long, PensionerDetails> getPensioners() {
		return pensionerDetailsService.getPensioners();
	}

}

package com.example.demo.service;

import java.util.Map;

import com.example.demo.entity.PensionerDetails;

public interface PensionerDetailsServiceInterface {

	PensionerDetails getPensionerDetails(long aadharNumber);
	
	public Map<Long, PensionerDetails> getPensioners();

}
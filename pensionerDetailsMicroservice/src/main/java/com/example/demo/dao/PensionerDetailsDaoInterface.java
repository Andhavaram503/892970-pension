package com.example.demo.dao;

import java.util.Map;

import com.example.demo.entity.PensionerDetails;

public interface PensionerDetailsDaoInterface {

	PensionerDetails getPensionerDetails(long aadharNumber);

	public Map<Long, PensionerDetails> getPensioners();

}
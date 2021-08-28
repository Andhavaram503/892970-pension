package com.example.demo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PensionerDetailsDaoInterface;
import com.example.demo.entity.PensionerDetails;

@Service
public class PensionerDetailsService implements PensionerDetailsServiceInterface {

	@Autowired
	private PensionerDetailsDaoInterface pensionerDetailsDao;
	
	public PensionerDetailsService(PensionerDetailsDaoInterface pensionerDetailsDao) {
		this.pensionerDetailsDao=pensionerDetailsDao;
	}

	@Override
	public PensionerDetails getPensionerDetails(long aadharNumber){
		return pensionerDetailsDao.getPensionerDetails(aadharNumber);

	}

	@Override
	public Map<Long, PensionerDetails> getPensioners() {
		return pensionerDetailsDao.getPensioners();
	}
}

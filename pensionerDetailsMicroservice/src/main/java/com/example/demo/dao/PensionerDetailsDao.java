package com.example.demo.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Bank;
import com.example.demo.entity.PensionerDetails;
import com.example.demo.exception.AadharNotFoundException;
import com.example.demo.util.DateUtil;

@Component
public class PensionerDetailsDao implements PensionerDetailsDaoInterface {

	private Map<Long, PensionerDetails> pensionerDetails;

	public PensionerDetailsDao() throws NumberFormatException, IOException, ParseException {
		String str = "";
		pensionerDetails = new HashMap<>();
		BufferedReader br = new BufferedReader(
				new InputStreamReader(this.getClass().getResourceAsStream("/details.csv")));

		while ((str = br.readLine()) != null) {

			String[] pensioner = str.split("\t");

			PensionerDetails details = new PensionerDetails(pensioner[1], DateUtil.parseDate(pensioner[2]),
					pensioner[3], Double.parseDouble(pensioner[4]), Double.parseDouble(pensioner[5]), pensioner[6],
					new Bank(pensioner[7], Long.parseLong(pensioner[8]), pensioner[9]));
			pensionerDetails.put(Long.parseLong(pensioner[0]), details);

		}
	}

	@Override
	public PensionerDetails getPensionerDetails(long aadharNumber) {
		if (pensionerDetails.containsKey(aadharNumber)) {
			return pensionerDetails.get(aadharNumber);
		} else {
			throw new AadharNotFoundException("Aadhar not found");
		}
	}

	public Map<Long, PensionerDetails> getPensioners() {
		if (pensionerDetails.isEmpty()) {
			throw new AadharNotFoundException("No User Found");
		} else {
			return pensionerDetails;
		}
	}

}

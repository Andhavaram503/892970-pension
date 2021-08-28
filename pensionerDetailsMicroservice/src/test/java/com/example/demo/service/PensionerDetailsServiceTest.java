package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.PensionerDetailsDaoInterface;
import com.example.demo.entity.Bank;
import com.example.demo.entity.PensionerDetails;
import com.example.demo.exception.AadharNotFoundException;

@SpringBootTest
class PensionerDetailsServiceTest {

	static private Map<Long, PensionerDetails> pensionerDetails = new HashMap<>();

	static PensionerDetails details = new PensionerDetails("komali", new Date(), "SIALK81689", 91830, 1600, "self",
			new Bank("HDFC", 64828268, "public"));

	static {
		pensionerDetails.put((long) 12345678, details);
	}

	@Mock
	PensionerDetailsDaoInterface detailsDaoInterface;

	@Test
	void getPensionerDetailsTest() throws NumberFormatException, IOException, ParseException, AadharNotFoundException {
		PensionerDetailsServiceInterface pensionerDetailsServiceInterface = new PensionerDetailsService(
				detailsDaoInterface);
		when(detailsDaoInterface.getPensionerDetails(12345678)).thenReturn(details);
		assertEquals(pensionerDetailsServiceInterface.getPensionerDetails(12345678).getPan(), "SIALK81689");

	}

}

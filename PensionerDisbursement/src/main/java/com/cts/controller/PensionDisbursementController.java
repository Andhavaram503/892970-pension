package com.cts.controller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.client.PensionerDetailClient;
import com.cts.exception.AadharNotFoundException;
import com.cts.model.ProcessPensionInput;
import com.cts.model.ProcessPensionResponse;
import com.cts.service.PensionerDisbursementServiceInterface;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PensionDisbursementController {

	@Autowired
	private PensionerDetailClient pdc;
	@Autowired
	private PensionerDisbursementServiceInterface pdsi;

	@PostMapping("/DisbursePension")
	public ProcessPensionResponse getResponse(@RequestBody ProcessPensionInput processPensionInput)
			throws NumberFormatException, IOException, ParseException, AadharNotFoundException {
		log.info("Pension Disbursment process starts here");
		log.info("Pension Disbursment process Ends here");
		return pdsi.getResponse(pdc.getDetails(processPensionInput.getAadharNumber()).getBank(),
				processPensionInput.getBankType());

	}

}

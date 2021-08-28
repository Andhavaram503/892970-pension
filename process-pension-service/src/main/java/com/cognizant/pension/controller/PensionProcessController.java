package com.cognizant.pension.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.pension.clients.PensionDisbursementClient;
import com.cognizant.pension.clients.PensionerDetailsClient;
import com.cognizant.pension.model.PensionDetail;
import com.cognizant.pension.model.PensionerDetails;
import com.cognizant.pension.model.PensionerInput;
import com.cognizant.pension.model.ProcessPensionInput;
import com.cognizant.pension.model.ProcessPensionResponse;
import com.cognizant.pension.service.PensionProcessService;

@RestController
public class PensionProcessController {
	
	@Autowired
	private PensionerDetailsClient pensionerDetailsClient;
	
	@Autowired
	private PensionDisbursementClient pensionDisbursementClient;
	
	@Autowired
	private PensionProcessService pensionProcessService; 
	
	
	
	@PostMapping("/PensionDetail")
	//@ModelAttribute("") PensionerInput should be model attribute
	public PensionDetail getPensionDetails(@RequestBody PensionerInput pensionerInput) throws Exception
	{
		PensionerDetails pensionerDetail = pensionerDetailsClient.getDetails(pensionerInput.getAadhar());		
		return pensionProcessService.getPensionDetails(pensionerDetail, pensionerInput);
		
	}
	
	@PostMapping("/process-pension")
	public ProcessPensionResponse getProcessPension(@RequestBody ProcessPensionInput processPensionInput) throws Exception 
	{
		
		//PensionerDetails pensionerDetails = pensionerDetailsClient.getDetails(processPensionInput.getAadharNumber());
		
		ProcessPensionResponse  processPensionResponse = pensionDisbursementClient.getResponse(processPensionInput);
		
		/*
		 * if(processPensionResponse.getStatus_code() == PENSION_DISBURSEMENT_SUCCESS) {
		 * return PENSION_DISBURSEMENT_SUCCESS; }
		 * 
		 * String bankType = pensionerDetails.getBank().getBankType();
		 * 
		 * return PENSION_DISBURSEMENT_FAILURE;
		 * 
		 */
		return pensionProcessService.getProcessPension(pensionDisbursementClient, processPensionResponse, processPensionInput);
		
		
	}
		

}

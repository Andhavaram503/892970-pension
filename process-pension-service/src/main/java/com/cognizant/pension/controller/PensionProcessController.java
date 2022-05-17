package com.cognizant.pension.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PensionProcessController {
	
	@Autowired
	private PensionerDetailsClient pensionerDetailsClient;
	
	@Autowired
	private PensionDisbursementClient pensionDisbursementClient;
	
	@Autowired
	private PensionProcessService pensionProcessService; 
	

	private static final int PENSION_DISBURSEMENT_SUCCESS=10; 
	
	private static final int PENSION_DISBURSEMENT_FAILURE=21;
	
	@PostMapping("/PensionDetail")
	public PensionDetail getPensionDetails(@RequestBody PensionerInput pensionerInput) throws Exception
	{
		log.info("inside getPensionDetails method");
		PensionerDetails pensionerDetail = pensionerDetailsClient.getDetails(pensionerInput.getAadhar());	
		log.info("inside getPensionDetails method {} --> ",pensionerDetail);
		return pensionProcessService.getPensionDetails(pensionerDetail, pensionerInput);
		
	}
	
	@PostMapping("/process-pension")
	public ProcessPensionResponse getProcessPension(@RequestBody ProcessPensionInput processPensionInput) throws Exception 
	{
				log.info("inside getProcessPension method ");
				ProcessPensionResponse  processPensionResponse = pensionDisbursementClient.getResponse(processPensionInput);
				log.info("Recieved processPensionResponse from pensionDisbursementClient ");
				if(processPensionResponse.getStatus_code() == PENSION_DISBURSEMENT_FAILURE)
				{
					log.info("inside getProcesspension....... PENSION_DISBURSEMENT_FAILURE coming");
					for(int i=0;i<3;i++)
					{
						log.info("inside getProcesspension....... PENSION_DISBURSEMENT_FAILURE coming hitting service {}",i);
						processPensionResponse = pensionDisbursementClient.getResponse(processPensionInput);
						if(processPensionResponse.getStatus_code() == PENSION_DISBURSEMENT_SUCCESS)
						{
							log.info("inside getProcesspension....... PENSION_DISBURSEMENT_SUCCESS coming hitting service {}",i);
							return processPensionResponse;
						}
					}
				}
				log.info("inside getProcesspension....... returning response");
				return processPensionResponse;
		
	}
		

}

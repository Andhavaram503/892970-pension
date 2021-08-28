package com.cognizant.pension.service;

import org.springframework.stereotype.Service;

import com.cognizant.pension.clients.PensionDisbursementClient;
import com.cognizant.pension.model.PensionDetail;
import com.cognizant.pension.model.PensionerDetails;
import com.cognizant.pension.model.PensionerInput;
import com.cognizant.pension.model.ProcessPensionInput;
import com.cognizant.pension.model.ProcessPensionResponse;

@Service
public class PensionProcessService {
	 
	private static final int PENSION_DISBURSEMENT_SUCCESS=10; 
	
	private static final int PENSION_DISBURSEMENT_FAILURE=21;
	
	private static final int BANK_TYPE_PUBLIC=500; 
	
	private static final int BANK_TYPE_PRIVATE=550;
	
	public PensionDetail getPensionDetails( PensionerDetails pensionerDetail, PensionerInput pensionerInput) 
	{
				
		if(pensionerDetail == null)
		{
			return null;
		}
		
		else 
		{			
			double salary = pensionerDetail.getSalaryEarned();
			double allowances = pensionerDetail.getAllowances();
			String pensionType= pensionerInput.getPensionType();
			double pensionAmount=0;
			if(pensionType.equalsIgnoreCase(pensionerDetail.getPensionType()))
			{
				if(pensionerDetail.getBank().getBankType() .equalsIgnoreCase("public"))
				{
					pensionAmount=(0.8 * salary)+allowances;	
					pensionAmount -= BANK_TYPE_PUBLIC;
				}
				else
				{
					pensionAmount=(0.8 * salary)+allowances;	
					pensionAmount-= BANK_TYPE_PRIVATE;
				}
			}
			else {
				
				if(pensionerDetail.getBank().getBankType() .equalsIgnoreCase("public"))
				{
					pensionAmount=(0.5 * salary)+allowances;	
					pensionAmount -= BANK_TYPE_PUBLIC;
				}
				else
				{
					pensionAmount=(0.5 * salary)+allowances;	
					pensionAmount -= BANK_TYPE_PRIVATE;
				}
								
			}
		return new PensionDetail(pensionerDetail.getName(), pensionerDetail.getDateOfBirth() , pensionerDetail.getPan(), pensionerDetail.getPensionType(), pensionAmount);
		}
		
	}

	public ProcessPensionResponse getProcessPension(PensionDisbursementClient pensionDisbursementClient, ProcessPensionResponse processPensionResponse, ProcessPensionInput processPensionInput) throws Exception {

		if(processPensionResponse.getStatus_code() == PENSION_DISBURSEMENT_FAILURE)
		{
			for(int i=0;i<3;i++)
			{
				processPensionResponse = pensionDisbursementClient.getResponse(processPensionInput);
				if(processPensionResponse.getStatus_code() == PENSION_DISBURSEMENT_SUCCESS)
				{
					return processPensionResponse;
				}
			}
		}
		return processPensionResponse;
		
	}
	
	
	
	
	
}

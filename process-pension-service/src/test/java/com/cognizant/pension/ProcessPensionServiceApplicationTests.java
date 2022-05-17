package com.cognizant.pension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.pension.clients.PensionDisbursementClient;
import com.cognizant.pension.model.Bank;
import com.cognizant.pension.model.PensionDetail;
import com.cognizant.pension.model.PensionerDetails;
import com.cognizant.pension.model.PensionerInput;
import com.cognizant.pension.model.ProcessPensionInput;
import com.cognizant.pension.model.ProcessPensionResponse;
import com.cognizant.pension.service.PensionProcessService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ProcessPensionServiceApplicationTests {

	// Service Layer Testing	
	
	@Mock
	private PensionProcessService pensionProcessService; 
	
	@Mock
	private PensionDisbursementClient pensionDisbursementClient; 

	
	
	@Test
	@Order(1)
	void  getPensionDetails_Self_public_bank() throws NoSuchFieldException, SecurityException {
		
		PensionerDetails pensionerDetail= new PensionerDetails("Srikanth", new Date(), "ABCD2121B", 100000, 1500, "Self", new Bank("Bharath", 1253156231, "public"));
		PensionerInput pensionerInput=new PensionerInput(1, "Srikanth",new Date(), "ABCD2121B" ,"456545165", "Self");
		PensionDetail pensionDetail =new PensionDetail(pensionerDetail.getName(), pensionerDetail.getDateOfBirth() , pensionerDetail.getPan(), pensionerDetail.getPensionType(), 81000);
		when(pensionProcessService.getPensionDetails(pensionerDetail, pensionerInput)).   
				thenReturn(pensionDetail);
		assertNotNull(pensionProcessService.getPensionDetails(pensionerDetail, pensionerInput));
		PensionDetail amt = pensionProcessService.getPensionDetails(pensionerDetail, pensionerInput);
		assertEquals(81000, amt.getPensionAmount());
		
	}
	
	@Test
	@Order(2)
	void  getPensionDetails_Self_private_bank() throws NoSuchFieldException, SecurityException {
		
//		PensionerDetails pensionerDetail= new PensionerDetails();
		PensionerDetails pensionerDetail=new PensionerDetails("Srikanth", new Date(), "ABCD2121B", 100000, 1500, "Self", new Bank("Bharath", 1253156231, "public"));	
		PensionerInput pensionerInput=new PensionerInput(1, "Srikanth",new Date(), "ABCD2121B" ,"456545165", "Self");
		PensionDetail pensionDetail =new PensionDetail(pensionerDetail.getName(), pensionerDetail.getDateOfBirth() , pensionerDetail.getPan(), pensionerDetail.getPensionType(), 80950);
		when(pensionProcessService.getPensionDetails(pensionerDetail, pensionerInput)).   
				thenReturn(pensionDetail);
		assertNotNull(pensionProcessService.getPensionDetails(pensionerDetail, pensionerInput));
		assertEquals(80950, (pensionProcessService.getPensionDetails(pensionerDetail, pensionerInput)).getPensionAmount());
		//System.err.println(pensionDetail.getClass().getDeclaredField("pensionType"));
	}
	
	@Test
	@Order(3)
	void  getPensionDetails_family_public_bank() throws NoSuchFieldException, SecurityException {
		
		PensionerDetails pensionerDetail= new PensionerDetails();
		PensionerInput pensionerInput=new PensionerInput(1, "Srikanth",new Date(), "ABCD2121B" ,"456545165", "family");
		PensionDetail pensionDetail =new PensionDetail(pensionerDetail.getName(), pensionerDetail.getDateOfBirth() , pensionerDetail.getPan(), pensionerDetail.getPensionType(), 51000);
		when(pensionProcessService.getPensionDetails(pensionerDetail, pensionerInput)).   
				thenReturn(pensionDetail);
		assertNotNull(pensionProcessService.getPensionDetails(pensionerDetail, pensionerInput));
		PensionDetail amt = pensionProcessService.getPensionDetails(pensionerDetail, pensionerInput);
		assertEquals(51000, amt.getPensionAmount());
		System.err.println(pensionDetail.getDateOfBirth());
	}
	
	@Test
	@Order(4)
	void  getPensionDetails_family_private_bank() throws NoSuchFieldException, SecurityException {
		
		PensionerDetails pensionerDetail=new PensionerDetails("Srikanth", new Date(), "ABCD2121B", 100000, 1500, "Self", new Bank("Bharath", 1253156231, "public"));	
		PensionerInput pensionerInput=new PensionerInput(1, "Srikanth",new Date(), "ABCD2121B" ,"456545165", "family");
		PensionDetail pensionDetail =new PensionDetail(pensionerDetail.getName(), pensionerDetail.getDateOfBirth() , pensionerDetail.getPan(), pensionerDetail.getPensionType(), 50950);
		when(pensionProcessService.getPensionDetails(pensionerDetail, pensionerInput)).   
				thenReturn(pensionDetail);
		assertNotNull(pensionProcessService.getPensionDetails(pensionerDetail, pensionerInput));
		assertEquals(50950, (pensionProcessService.getPensionDetails(pensionerDetail, pensionerInput)).getPensionAmount());
		//System.err.println(pensionDetail.getClass().getDeclaredField("pensionType"));
	}
	
	
	@Test
	@Order(5)
	 void getProcessPension_success_Test(  ) throws Exception  
	{
		ProcessPensionInput processPensionInput=new ProcessPensionInput("454151", 100000.00, "public" );
		ProcessPensionResponse processPensionResponse=new ProcessPensionResponse(10);
		when(pensionProcessService.getProcessPension(pensionDisbursementClient,processPensionResponse,processPensionInput)).thenReturn(processPensionResponse);
		assertNotNull(pensionProcessService.getProcessPension(pensionDisbursementClient,processPensionResponse,processPensionInput).getStatus_code());
		assertEquals(10, pensionProcessService.getProcessPension(pensionDisbursementClient,processPensionResponse,processPensionInput).getStatus_code());
		System.err.println(pensionProcessService.getProcessPension(pensionDisbursementClient,processPensionResponse,processPensionInput).getStatus_code());
	}
	
	@Test
	@Order(6)
	 void getProcessPension_fail_Test(  ) throws Exception  
	{
		ProcessPensionInput processPensionInput=new ProcessPensionInput("454151", 100000.00, "public" );
		ProcessPensionResponse processPensionResponse=new ProcessPensionResponse(21);
		when(pensionProcessService.getProcessPension(pensionDisbursementClient,processPensionResponse,processPensionInput)).thenReturn(processPensionResponse);
		assertNotNull(pensionProcessService.getProcessPension(pensionDisbursementClient,processPensionResponse,processPensionInput).getStatus_code());
		assertEquals(21, pensionProcessService.getProcessPension(pensionDisbursementClient,processPensionResponse,processPensionInput).getStatus_code());
		System.err.println(pensionProcessService.getProcessPension(pensionDisbursementClient,processPensionResponse,processPensionInput).getStatus_code());
	}
	@Test
	@Order(7)
	 void getProcessPension_success_private_Test(  ) throws Exception  
	{
		ProcessPensionInput processPensionInput=new ProcessPensionInput("454151", 100000.00, "private" );
		ProcessPensionResponse processPensionResponse=new ProcessPensionResponse(10);
		when(pensionProcessService.getProcessPension(pensionDisbursementClient,processPensionResponse,processPensionInput)).thenReturn(processPensionResponse);
		assertNotNull(pensionProcessService.getProcessPension(pensionDisbursementClient,processPensionResponse,processPensionInput).getStatus_code());
		assertEquals(10, pensionProcessService.getProcessPension(pensionDisbursementClient,processPensionResponse,processPensionInput).getStatus_code());
		System.err.println(pensionProcessService.getProcessPension(pensionDisbursementClient,processPensionResponse,processPensionInput).getStatus_code());
	}
	
	@Test
	@Order(8)
	 void getProcessPension_fail_private_Test(  ) throws Exception  
	{
		ProcessPensionInput processPensionInput=new ProcessPensionInput("454151", 100000.00, "private" );
		ProcessPensionResponse processPensionResponse=new ProcessPensionResponse(21);
		when(pensionProcessService.getProcessPension(pensionDisbursementClient,processPensionResponse,processPensionInput)).thenReturn(processPensionResponse);
		assertNotNull(pensionProcessService.getProcessPension(pensionDisbursementClient,processPensionResponse,processPensionInput).getStatus_code());
		assertEquals(21, pensionProcessService.getProcessPension(pensionDisbursementClient,processPensionResponse,processPensionInput).getStatus_code());
		System.err.println(pensionProcessService.getProcessPension(pensionDisbursementClient,processPensionResponse,processPensionInput).getStatus_code());
	}
	
	
}

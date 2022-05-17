package com.cognizant.pension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.pension.model.AuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc

class PensionManagementPortalApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	private MockHttpServletResponse response;
	
	@Test
	void testGetPensionDetails() throws Exception {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest("kranthi", "kranthi");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(authenticationRequest);
		response=mockMvc.perform(get("/pensioners").contentType(MediaType.APPLICATION_JSON).content(json)).andReturn().getResponse();
		assertNotNull(response);
	}
	@Test
	void testGetPensionDetails2() throws Exception {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest("kranthi", "kranthi");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(authenticationRequest);
		response=mockMvc.perform(get("/pensioners").contentType(MediaType.APPLICATION_JSON).content(json)).andReturn().getResponse();
		assertNotNull(response);
	}
	

}

package com.example.demo;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.model.ProcessPensionInput;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class PensionerDisbursementApplicationTests {
	@Autowired
	private MockMvc mvc;
	
	@Test
	void testGetResponse() throws Exception {
		ProcessPensionInput processPensionInput=new ProcessPensionInput(12345620,81530.2,"private");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(processPensionInput);
		mvc.perform(post("/DisbursePension").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
	}
	    
}

package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PensionDetailsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getPensionDetailsTest1() throws Exception {
		mockMvc.perform(get("/details/123456789012")).andExpect(status().isNotFound());
	}
	
	@Test
	void getPensionDetailsTest2() throws Exception {
		mockMvc.perform(get("/details/12345620")).andExpect(status().isOk());
	}
	
	@Test
	void getPensionersTest() throws Exception {
		mockMvc.perform(get("/pensions")).andExpect(status().isOk());
	}
	
//	/pensions
}

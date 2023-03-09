package com.safetynet.saftynetalerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@AutoConfigureMockMvc
public class URLControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void getFirestation_Ok_Test() throws Exception {	
		mockMvc.perform(get("/firestation")
				.param("stationNumber", "1"))
		.andExpect(status().isAccepted());
	}
	
	@Test
	void getFirestation_NotFound_Test() throws Exception {	
		mockMvc.perform(get("/firestation")
				.param("stationNumber", "0"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	void getChildAlert_Ok_Test() throws Exception {	
		mockMvc.perform(get("/childAlert")
				.param("address", "1509 Culver St"))
		.andExpect(status().isAccepted());
	}
	
	@Test
	void getPhoneAlert_Ok_Test() throws Exception {	
		mockMvc.perform(get("/phoneAlert")
				.param("firestation", "1"))
		.andExpect(status().isAccepted());
	}
	
	@Test
	void getFire_Ok_Test() throws Exception {	
		mockMvc.perform(get("/fire")
				.param("address", "1509 Culver St"))
		.andExpect(status().isAccepted());
	}
	
	@Test
	void getFlood_Ok_Test () throws Exception {
		mockMvc.perform(get("/flood/stations")
				.param("stations", "1", "3"))
		.andExpect(status().isAccepted());
	}
	
	@Test
	void getPersonInfo_Ok_Test() throws Exception {	
		mockMvc.perform(get("/personInfo")
				.param("firstName", "Jacob")
				.param("lastName", "Boyd"))
		.andExpect(status().isAccepted());
	}
}

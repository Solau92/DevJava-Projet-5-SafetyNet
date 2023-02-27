package com.safetynet.saftynetalerts;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class URLControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getFirestation_Ok_Test() throws Exception {	
		mockMvc.perform(get("/firestation")
				.param("stationNumber", "1"))
		.andExpect(status().isAccepted());
	}
	
	@Test
	public void getFirestation_NotFound_Test() throws Exception {	
		mockMvc.perform(get("/firestation")
				.param("stationNumber", "0"))
		.andExpect(status().isNotFound());
		// Marche pas car pas géré dans ma méthode ! 
	}
	
	@Test
	public void getChildAlert_Ok_Test() throws Exception {	
		mockMvc.perform(get("/childAlert")
				.param("address", "1509 Culver St"))
		.andExpect(status().isAccepted());
	}
	
	@Test
	public void getPhoneAlert_Ok_Test() throws Exception {	
		mockMvc.perform(get("/phoneAlert")
				.param("firestation", "1"))
		.andExpect(status().isAccepted());
	}
	
	@Test
	public void getFire_Ok_Test() throws Exception {	
		mockMvc.perform(get("/fire")
				.param("address", "1509 Culver St"))
		.andExpect(status().isAccepted());
	}
	
	@Test
	public void getPersonInfo_Ok_Test() throws Exception {	
		mockMvc.perform(get("/personInfo")
				.param("firstName", "John")
				.param("lastName", "Boyd"))
		.andExpect(status().isAccepted());
	}
}

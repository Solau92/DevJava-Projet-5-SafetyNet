package com.safetynet.saftynetalerts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class FirestationsControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getFirestationsTest() throws Exception {
		mockMvc.perform(get("/firestations"))
		.andExpect(status().isAccepted())
		.andExpect(jsonPath("$[0].address", is("1509 Culver St")))
		.andExpect(jsonPath("$[1].address", is("29 15th St")));
	}
	
	@Test
	public void postFirestation_Ok_Test() throws Exception {
		mockMvc.perform(post("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "  \"address\":\"adresse immeuble\",\r\n"
						+ "  \"station\": 100\r\n"
						+ "}"))	
		.andExpect(status().isCreated());
	}
	
	@Test
	public void putFirestation_Ok_Test() throws Exception {
		mockMvc.perform(put("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "  \"address\":\"adresse immeuble\",\r\n"
						+ "  \"station\": 100\r\n"
						+ "}"))	
		.andExpect(status().isAccepted());
	}
	
	@Test
	public void deleteFirestation_Ok_Test() throws Exception {
		mockMvc.perform(delete("/firestation")
				.param("address", "address"))
		.andExpect(status().isAccepted());
	}
	
}

package com.safetynet.safetynetalerts.IT;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AdviceControllerTestIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void postPerson_Exception_Test() throws Exception {

		JSONObject person = new JSONObject();
		person.put("firstName", "Jacob");
		person.put("lastName", "Boyd");
		person.put("address", "myAddress");
		person.put("city", "myCity");
		person.put("zip", "myZip");
		person.put("phone", "myPhone");
		person.put("email", "myEmail");
		String jsonContent = person.toString();

		mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(jsonContent))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	void putPerson_Exception_Test() throws Exception {
		
		JSONObject person = new JSONObject();
		person.put("firstName", "notFoundFirstName");
		person.put("lastName", "notFoundLastName");
		person.put("address", "myAddress");
		person.put("city", "myCity");
		person.put("zip", "myZip");
		person.put("phone", "myPhone");
		person.put("email", "myEmail");
		String jsonContent = person.toString();
		
		mockMvc.perform(put("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonContent))	
		.andExpect(status().isNotFound());
	}
	
	@Test
	void postFirestation_Exception_Test() throws Exception {
		
		JSONObject firestation = new JSONObject();
		firestation.put("address", "1509 Culver St");
		firestation.put("station", "100");
		String jsonContent = firestation.toString();
		
		mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON)
				.content(jsonContent))
				.andExpect(status().isBadRequest());
	}

	@Test
	void putFirestation_Exception_Test() throws Exception {
		
		JSONObject firestation = new JSONObject();
		firestation.put("address", "building address notFound");
		firestation.put("station", "100");
		String jsonContent = firestation.toString();
		
		mockMvc.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON)
				.content(jsonContent))
				.andExpect(status().isNotFound());
	}
	
	@Test
	void postMedicalRecored_Exception_Test() throws Exception {
		
		mockMvc.perform(post("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "  \"firstName\":\"John\",\r\n"
						+ "  \"lastName\":\"Boyd\",\r\n"
						+ "  \"birthdate\":\"07/14/1984\",\r\n"
						+ "  \"medications\":[\r\n"
						+ "            \"medication1\",\r\n"
						+ "            \"medication2\"\r\n"
						+ "        ],\r\n"
						+ "  \"allergies\":[\r\n"
						+ "            \"allergy1\",\r\n"
						+ "            \"allergy2\"\r\n"
						+ "        ]\r\n"
						+ "}"))	
		.andExpect(status().isBadRequest());
	}
	
	@Test
	void putMedicalRecord_Exception_Test() throws Exception {
		mockMvc.perform(put("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "  \"firstName\":\"firstNameNotFound\",\r\n"
						+ "  \"lastName\":\"lastNameNotFound\",\r\n"
						+ "  \"birthdate\":\"07/14/1984\",\r\n"
						+ "  \"medications\":[\r\n"
						+ "            \"medication1\",\r\n"
						+ "            \"medication2\"\r\n"
						+ "        ],\r\n"
						+ "  \"allergies\":[\r\n"
						+ "            \"allergy1\",\r\n"
						+ "            \"allergy2\"\r\n"
						+ "        ]\r\n"
						+ "}"))	
		.andExpect(status().isNotFound());
	}


}

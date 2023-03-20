package com.safetynet.safetynetalerts.IT;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MedicalRecordsControllerTestIT {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void getMedicalRecordsTest() throws Exception {
		
		mockMvc.perform(get("/medicalRecords"))
		.andExpect(status().isAccepted())
		.andExpect(jsonPath("$[0].birthdate", is("03/06/1984")))
		.andExpect(jsonPath("$[1].lastName", is("Boyd")));
	}

	@Test
	void postMedicalRecored_Ok_Test() throws Exception {
		
//		JSONObject medicalRecord = new JSONObject();
//		medicalRecord.put("firstName", "firstName");
//		medicalRecord.put("lastName", "lastName");
//		medicalRecord.put("birthdate", "07/14/1984");
//		List<String> medication = new ArrayList<>();
//		medication.add("medication1");
//		medication.add("medication2");
//		medicalRecord.put("medication", medication.toString());
//		List<String> allergies = new ArrayList<>();
//		allergies.add("allergy1");
//		allergies.add("allergy2");
//		medicalRecord.put("allergies", allergies.toString());
//		
//		String jsonContent = medicalRecord.toString();

		mockMvc.perform(post("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
//				.content(jsonContent))
				.content("{\r\n"
						+ "  \"firstName\":\"firstName\",\r\n"
						+ "  \"lastName\":\"lastName\",\r\n"
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
		.andExpect(status().isCreated());
	}
	
	@Test
	void putMedicalRecord_Ok_Test() throws Exception {
		mockMvc.perform(put("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "  \"firstName\":\"Jacob\",\r\n"
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
		.andExpect(status().isAccepted());
	}
	
	@Test
	void deletMedicalRecord_Ok_Test() throws Exception {
		mockMvc.perform(delete("/medicalRecord")
				.param("firstName", "firstName")
				.param("lastName", "lastName"))
		.andExpect(status().isAccepted());
	}
}

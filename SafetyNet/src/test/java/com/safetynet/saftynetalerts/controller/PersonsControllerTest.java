package com.safetynet.saftynetalerts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
public class PersonsControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void getPersonsTest() throws Exception {
		mockMvc.perform(get("/persons"))
		.andExpect(status().isAccepted())
		.andExpect(jsonPath("$[0].firstName", is("John")))
		.andExpect(jsonPath("$[1].firstName", is("Jacob")));
	}
	
	@Test
	void postPerson_Ok_Test() throws Exception {
		JSONObject toto = new JSONObject();
		toto.put("firstname", "toto");
		String jsonContent = toto.toString();
		mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "  \"firstName\":\"Sophie\",\r\n"
						+ "  \"lastName\":\"LAURENT\",\r\n"
						+ "  \"address\":\"myAddress\",\r\n"
						+ "  \"city\":\"myCity\",\r\n"
						+ "  \"zip\":\"myZip\",\r\n"
						+ "  \"phone\":\"myPhone\",\r\n"
						+ "  \"email\":\"myEmail\"\r\n"
						+ "}"))	
		.andExpect(status().isCreated());
	}

//	@Test
//	public void postPerson_null_Test() throws Exception {
//		mockMvc.perform(post("/person")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(null))	
//		.andExpect(status().isBadRequest());
//	}
	
	@Test
	void putPerson_Ok_Test() throws Exception {
		mockMvc.perform(put("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "  \"firstName\":\"Sophie\",\r\n"
						+ "  \"lastName\":\"LAURENT\",\r\n"
						+ "  \"address\":\"myAddress\",\r\n"
						+ "  \"city\":\"myCity\",\r\n"
						+ "  \"zip\":\"myZip\",\r\n"
						+ "  \"phone\":\"myPhone\",\r\n"
						+ "  \"email\":\"myEmail\"\r\n"
						+ "}"))	
		.andExpect(status().isAccepted());
	}
	
	@Test
	void deletPerson_Ok_Test() throws Exception {
		mockMvc.perform(delete("/person")
				.param("firstName", "firstName")
				.param("lastName", "lastName"))
		.andExpect(status().isAccepted());
	}
	
	@Test
	void getPersonByLastName_Ok_Test() throws Exception {
		mockMvc.perform(get("/person")
				.param("lastName", "Boyd"))
		.andExpect(status().isAccepted());
	}
	
	@Test
	void getPersonByLastName_NotFound_Test() throws Exception {
		mockMvc.perform(get("/person")
				.param("lastName", "lastName"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	void getPersonByAddress_Ok_Test() throws Exception {
		mockMvc.perform(get("/personByAddress")
				.param("address", "address"))
		.andExpect(status().isOk());
	}
	
	@Test
	void getCommunityEmail_Ok_Test() throws Exception {
		mockMvc.perform(get("/communityEmail")
				.param("city", "city"))
		.andExpect(status().isOk());
	}
	
}

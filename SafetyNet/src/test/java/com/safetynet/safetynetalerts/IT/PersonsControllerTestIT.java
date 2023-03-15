package com.safetynet.safetynetalerts.IT;

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
class PersonsControllerTestIT {
	
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
		
		JSONObject person = new JSONObject();
		person.put("firstName", "Sophie");
		person.put("lastName", "LAURENT");
		person.put("address", "myAddress");
		person.put("city", "myCity");
		person.put("zip", "myZip");
		person.put("phone", "myPhone");
		person.put("email", "myEmail");
		String jsonContent = person.toString();
		
		mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonContent))
		.andExpect(status().isCreated());
	}
	
	@Test
	void putPerson_Ok_Test() throws Exception {
		
		JSONObject person = new JSONObject();
		person.put("firstName", "Sophie");
		person.put("lastName", "LAURENT");
		person.put("address", "myAddress");
		person.put("city", "myCity");
		person.put("zip", "myZip");
		person.put("phone", "myPhone");
		person.put("email", "myEmail");
		String jsonContent = person.toString();
		
		mockMvc.perform(put("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonContent))	
		.andExpect(status().isAccepted());
	}
	
	@Test
	void deletPerson_Ok_Test() throws Exception {
		mockMvc.perform(delete("/person")
				.param("firstName", "John")
				.param("lastName", "Boyd"))
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
				.param("address", "748 Townings Dr"))
		.andExpect(status().isAccepted());
	}
	
	@Test
	void getCommunityEmail_Ok_Test() throws Exception {
		mockMvc.perform(get("/communityEmail")
				.param("city", "Culver"))
		.andExpect(status().isAccepted());
	}
	
}

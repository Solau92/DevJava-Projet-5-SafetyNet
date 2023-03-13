package com.safetynet.safetynetalerts.IT;

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
class FirestationsControllerTestIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getFirestationsTest() throws Exception {
		mockMvc.perform(get("/firestations")).andExpect(status().isAccepted())
				.andExpect(jsonPath("$[0].address", is("1509 Culver St")))
				.andExpect(jsonPath("$[1].address", is("29 15th St")));
	}

	@Test
	void postFirestation_Ok_Test() throws Exception {
		mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n" + "  \"address\":\"adresse immeuble\",\r\n" + "  \"station\": 100\r\n" + "}"))
				.andExpect(status().isCreated());
	}

	@Test
	void putFirestation_Ok_Test() throws Exception {
		mockMvc.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n" + "  \"address\":\"adresse immeuble\",\r\n" + "  \"station\": 100\r\n" + "}"))
				.andExpect(status().isAccepted());
	}

	@Test
	void deleteFirestation_Ok_Test() throws Exception {
		mockMvc.perform(delete("/firestation").param("address", "112 Steppes Pl")).andExpect(status().isAccepted());
	}

	@Test
	void getFirestationsAddressesById_Ok_Test() throws Exception {
		mockMvc.perform(get("/firestationById")
		.param("stationId", "4"))
		.andExpect(status().isAccepted());
	}

	@Test
	void getFirestationsByAddresse_Ok_Test() throws Exception {
		mockMvc.perform(get("/firestationByAddress")
		.param("address", "908 73rd St"))
		.andExpect(status().isAccepted());
	}
}

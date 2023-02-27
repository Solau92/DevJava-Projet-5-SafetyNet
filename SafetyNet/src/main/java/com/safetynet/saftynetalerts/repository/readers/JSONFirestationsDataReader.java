package com.safetynet.saftynetalerts.repository.readers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.saftynetalerts.model.FirestationSpot;

public class JSONFirestationsDataReader implements IDataFirestationsReader {

	String filePath;
	
	public JSONFirestationsDataReader(String filePath) {
		this.filePath = filePath;
	}
	

	@Override
	public List<FirestationSpot> readFirestations() throws IOException {
		
		File file = new File(filePath);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode firestationsNode = objectMapper.readTree(file).get("firestations");

		ObjectMapper objectMapper2 = new ObjectMapper();
		List<FirestationSpot> listFirestations = objectMapper2.readValue(firestationsNode.toString(), new TypeReference<List<FirestationSpot>>() {});
		
		return listFirestations;
	}

}
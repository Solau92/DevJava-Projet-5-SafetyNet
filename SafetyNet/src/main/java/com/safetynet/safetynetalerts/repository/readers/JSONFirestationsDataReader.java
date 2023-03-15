package com.safetynet.safetynetalerts.repository.readers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.Firestation;

public class JSONFirestationsDataReader implements IDataFirestationsReader {

	String filePath;
	
	public JSONFirestationsDataReader(String filePath) {
		this.filePath = filePath;
	}	

	/**
	 * Returns a list of firestations extracted from a JSON file.
	 * @return a list of firestations extracted from a JSON file
	 * @throws IOException
	 */
	@Override
	public List<Firestation> readFirestations() throws IOException {
		
		File file = new File(filePath);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode firestationsNode = objectMapper.readTree(file).get("firestations");

		ObjectMapper objectMapper2 = new ObjectMapper();
		
		return objectMapper2.readValue(firestationsNode.toString(), new TypeReference<List<Firestation>>() {});
	}

}

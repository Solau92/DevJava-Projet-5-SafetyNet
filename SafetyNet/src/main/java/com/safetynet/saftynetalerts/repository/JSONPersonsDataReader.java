package com.safetynet.saftynetalerts.repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.saftynetalerts.model.Person;

public class JSONPersonsDataReader implements IDataPersonsReader {

	String filePath;
	
//	@Override
//	public List<String> getString() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public JSONPersonsDataReader(String filePath) {
		this.filePath = filePath;
	}
	

	@Override
	public List<Person> readPersons() throws IOException {
		
		File file = new File(filePath);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode personsNode = objectMapper.readTree(file).get("persons");

		ObjectMapper objectMapper2 = new ObjectMapper();
		List<Person> listPersons = objectMapper2.readValue(personsNode.toString(), new TypeReference<List<Person>>() {});
		
		return listPersons;
	}

}

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

public class StringToObjectsTest {
	
	public static void StringToObject(String jsonString) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		Person person1 = objectMapper.readValue(jsonString, Person.class);
		
		System.out.println(person1.toString());
		
	}
	
	
	public static void ArrayStringToObjectsListVoid(String jsonString) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<Person> listPersons = objectMapper.readValue(jsonString, new TypeReference<List<Person>>() {});
		
		for(int i = 0 ; i < listPersons.size() ; i++) {
			System.out.println(i+1 + " : " + listPersons.get(i).toString());

		}

	}

	public static List<Person> ArrayStringToObjectsList(String jsonString) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<Person> listPersons = objectMapper.readValue(jsonString, new TypeReference<List<Person>>() {});
		
		return listPersons;

	}
	
	
	public static void JsonTreeToObjectsList(String filePath) throws IOException {
		
		File file = new File(filePath);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode objetNode = objectMapper.readTree(file);
		
		System.out.println(objetNode.toString());
		System.out.println(objetNode.get("firestations").toString());
		
		JsonNode personsNode = objetNode.get("persons");

		ArrayStringToObjectsListVoid(personsNode.toString());
		
		
	}

	
	
	
}

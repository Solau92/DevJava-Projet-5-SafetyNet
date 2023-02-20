package com.safetynet.saftynetalerts.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.saftynetalerts.model.Person;

import lombok.Data;

@Repository
@Data
public class Persons {
	
	private List<Person> persons;
	
//	public void createList(String filePath) throws IOException {
//		File file = new File(filePath);
//		ObjectMapper objectMapper = new ObjectMapper();
//		JsonNode personsNode = objectMapper.readTree(file).get("persons");
//		
//		this.persons = StringToObjectsTest.ArrayStringToObjectsList(personsNode.toString());
//	}

	

	public List<Person> getAllPersons() {

		return persons;
	}
	

}

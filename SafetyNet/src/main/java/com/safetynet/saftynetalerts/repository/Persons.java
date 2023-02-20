package com.safetynet.saftynetalerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

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

	public List<Person> getPersonsByName(String name) {

		List<Person> personsByName = new ArrayList<Person>();
		
		for(Person p : persons) {
			if (p.getLastName().equals(name)) {
				personsByName.add(p);
			}
		}
		return personsByName;
	}

//	public Person save(Person person) {
//
//		return person;
//	}

	public List<Person> getInhabitants(String address) {
		List<Person> inhabitants = new ArrayList<Person>();
		
		for(Person p : persons) {
			if (p.getAddress().equals(address)) {
				inhabitants.add(p);
			}
		}
		return inhabitants;
	}

	public List<Person> getPersonsByFirstNameAndLastName(String firstName, String lastName) {
		List<Person> personsByFirstNameAndLastName = new ArrayList<Person>();
		
		for(Person p : persons) {
			if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
				personsByFirstNameAndLastName.add(p);
			}
		}
		return personsByFirstNameAndLastName;
	}

	public List<String> getCommunityEmail(String city) {
		List<String> communityEmail = new ArrayList<String>();
		for(Person p : persons) {
			communityEmail.add(p.getEmail());
		}
		return communityEmail;
	}
	
	

}

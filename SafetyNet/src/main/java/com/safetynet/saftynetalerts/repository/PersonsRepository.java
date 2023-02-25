package com.safetynet.saftynetalerts.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.safetynet.saftynetalerts.model.Firestation;
import com.safetynet.saftynetalerts.model.Person;

import lombok.Data;

@Repository
@Data
public class PersonsRepository {

	private List<Person> persons;

//	@Autowired
//	private IDataPersonsWriter

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

		for (Person p : persons) {
			if (p.getLastName().equals(name)) {
				personsByName.add(p);
			}
		}
		return personsByName;
	}

	public List<Person> getPersonsByAddress(String address) {
		
		List<Person> inhabitants = new ArrayList<Person>();

		for (Person p : persons) {
			if (p.getAddress().equals(address)) {
				inhabitants.add(p);
			}
		}
		return inhabitants;
	}

	public List<Person> getPersonsByFirstNameAndLastName(String firstName, String lastName) {
		List<Person> personsByFirstNameAndLastName = new ArrayList<Person>();

		for (Person p : persons) {
			if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
				personsByFirstNameAndLastName.add(p);
			}
		}
		return personsByFirstNameAndLastName;
	}

	public List<String> getCommunityEmail(String city) {
		List<String> communityEmail = new ArrayList<String>();
		for (Person p : persons) {
			if (p.getCity().equals(city)) {
				communityEmail.add(p.getEmail());
			}
		}
		return communityEmail;
	}

	public Person save(Person person) throws StreamWriteException, DatabindException, IOException {
		persons.add(person);
		return person;
	}

	public Person update(Person person) {

		for (int i = 0 ; i < persons.size() ; i++) {
			if (persons.get(i).getFirstName().equals(person.getFirstName()) && persons.get(i).getLastName().equals(person.getLastName())) {
				persons.remove(persons.get(i));
			}
		}
		persons.add(person);
		return person;
	}

	public void deletePerson(String firstName, String lastName) {
		/*
		 * for (Person p : persons) { persons.remove(p); }
		 */
		for (int i = 0 ; i < persons.size() ; i++) {
			if (persons.get(i).getFirstName().equals(firstName) && persons.get(i).getLastName().equals(lastName)) {
				persons.remove(persons.get(i));
			}
		}
		
	}

	public List<Person> getPersonByLastNameAndAddress(String lastName, String address) {
		List<Person> personsByFirstNameAndAddress = new ArrayList<Person>();

		for (Person p : persons) {
			if (p.getLastName().equals(lastName) && p.getAddress().equals(address)) {
				personsByFirstNameAndAddress.add(p);
			}
		}
		return personsByFirstNameAndAddress;
	}

}

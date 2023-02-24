package com.safetynet.saftynetalerts.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.safetynet.saftynetalerts.model.Person;
import com.safetynet.saftynetalerts.repository.Persons;

import lombok.Data;

@Data
@Service
public class PersonService {
	
	@Autowired 
	private Persons persons;
	
	public List<Person> getAllPersons() {
		return persons.getAllPersons();
	}
	
	public List<Person> getPersonsByName(String name) {
		return persons.getPersonsByName(name);
	}

	public List<Person> getInhabitants(String address) {
		return persons.getInhabitants(address);
	}

	public List<Person> getPersonsByFirstNameAndLastName(String firstName, String lastName) {
		return persons.getPersonsByFirstNameAndLastName(firstName, lastName);
	}

	public List<String> getCommunityEmail(String city) {
		return persons.getCommunityEmail(city);
	}

	public Person savePerson(Person person) throws StreamWriteException, DatabindException, IOException {
		Person savedPerson = persons.save(person);
		return savedPerson;		
	}

	public Person updatePerson(Person person) {
		Person updatedPerson = persons.update(person);
		return updatedPerson;	
	}

	public void deletePerson(String firstName, String lastName) {
		persons.deletePerson(firstName, lastName);
	}
}

package com.safetynet.saftynetalerts.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.safetynet.saftynetalerts.exception.NotFoundException;
import com.safetynet.saftynetalerts.model.Person;
import com.safetynet.saftynetalerts.repository.PersonsRepository;

@Service
public class PersonService implements IPersonService {
	
	@Autowired 
	private PersonsRepository persons;
	
	// Essai exception 
	public List<Person> getAllPersons() throws NotFoundException {
		List<Person> list = persons.getAllPersons();
		if(list.isEmpty()) {
			throw new NotFoundException();
		}
		return persons.getAllPersons();
	}
	
	// Essai exception 
	public List<Person> getPersonsByLastName(String lastName) throws NotFoundException {
		List<Person> list = persons.getPersonsByLastName(lastName);
		if (list.isEmpty()) {
			throw new NotFoundException();
		}
		return list;
	}

	public List<Person> getPersonsByAddress(String address) {
		return persons.getPersonsByAddress(address);
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

	public Person updatePerson(Person person) throws NotFoundException {
		Person updatedPerson = persons.update(person);
		return updatedPerson;	
	}

	public void deletePerson(String firstName, String lastName) {
		persons.deletePerson(firstName, lastName);
	}

	public List<Person> getPersonByLastNameAndAddress(String lastName, String address) {
		return persons.getPersonByLastNameAndAddress(lastName, address);
	}
}

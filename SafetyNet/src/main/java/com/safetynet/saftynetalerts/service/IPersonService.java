package com.safetynet.saftynetalerts.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.safetynet.saftynetalerts.exception.NotFoundException;
import com.safetynet.saftynetalerts.model.Person;

public interface IPersonService {

	public List<Person> getAllPersons();
	
	public List<Person> getPersonsByLastName(String lastName) throws NotFoundException;

	public List<Person> getPersonsByAddress(String address);

	public List<Person> getPersonsByFirstNameAndLastName(String firstName, String lastName);

	public List<String> getCommunityEmail(String city);

	public Person savePerson(Person person) throws StreamWriteException, DatabindException, IOException;

	public Person updatePerson(Person person);

	public void deletePerson(String firstName, String lastName);

}

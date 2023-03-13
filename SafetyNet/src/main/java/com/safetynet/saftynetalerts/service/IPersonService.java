package com.safetynet.saftynetalerts.service;

import java.util.List;
import java.util.Set;

import com.safetynet.saftynetalerts.exception.PersonAlreadyExistsException;
import com.safetynet.saftynetalerts.exception.PersonNotFoundException;
import com.safetynet.saftynetalerts.model.Person;

public interface IPersonService {

	public List<Person> getAllPersons() throws PersonNotFoundException;
	
	public List<Person> getPersonsByLastName(String lastName) throws PersonNotFoundException;

	public List<Person> getPersonsByAddress(String address) throws PersonNotFoundException;

	public List<Person> getPersonsByFirstNameAndLastName(String firstName, String lastName) throws PersonNotFoundException;

	public Set<String> getCommunityEmail(String city);

	public Person savePerson(Person person) throws PersonAlreadyExistsException;

	public Person updatePerson(Person person) throws PersonNotFoundException;

	public void deletePerson(String firstName, String lastName) throws PersonNotFoundException;

	List<Person> getPersonsByLastNameAndAddress(String lastName, String address) throws PersonNotFoundException;

}

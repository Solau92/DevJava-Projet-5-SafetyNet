package com.safetynet.saftynetalerts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.exception.MoreThanOnePersonFoundException;
import com.safetynet.saftynetalerts.exception.PersonAlreadyExistsException;
import com.safetynet.saftynetalerts.exception.PersonNotFoundException;
import com.safetynet.saftynetalerts.model.Person;
import com.safetynet.saftynetalerts.repository.PersonsRepository;

@Service
public class PersonService implements IPersonService {
	
	private final PersonsRepository persons;
	
	public PersonService(PersonsRepository persons) {
		this.persons = persons;
	}
	
	public List<Person> getAllPersons() throws PersonNotFoundException {
		List<Person> list = persons.getAllPersons();
		if(list.isEmpty()) {
			throw new PersonNotFoundException("Nobody found");
		}
		return list;
	}
	
	public List<Person> getPersonsByLastName(String lastName) throws PersonNotFoundException {
		List<Person> list = persons.getPersonsByLastName(lastName);
		if (list.isEmpty()) {
			throw new PersonNotFoundException(lastName + " was not found");
		}
		return list;
	}

	public List<Person> getPersonsByAddress(String address) throws PersonNotFoundException {
		List<Person> list = persons.getPersonsByAddress(address);
		if(list.isEmpty()) {
			throw new PersonNotFoundException("Nobody found at this address : " + address);
		}
		return list;
	}

	public List<Person> getPersonsByFirstNameAndLastName(String firstName, String lastName) throws PersonNotFoundException, MoreThanOnePersonFoundException {
		List<Person> list = persons.getPersonsByFirstNameAndLastName(firstName, lastName);
		if(list.isEmpty()) {
			throw new PersonNotFoundException(firstName + " " + lastName + " was not found");
		} else if(list.size() > 1) {
			throw new MoreThanOnePersonFoundException("There's more than one " + firstName + " " + lastName);
		}
		return list;
	}

	public List<String> getCommunityEmail(String city) {
		return persons.getCommunityEmail(city);
	}

	public Person savePerson(Person person) throws PersonAlreadyExistsException {	
		if(!persons.getPersonsByFirstNameAndLastName(person.getFirstName(), person.getLastName()).isEmpty()) {
			throw new PersonAlreadyExistsException(person.getFirstName() + " " + person.getLastName() + " already exists");
		} else {
			return persons.save(person);		
		}		
	}

	public Person updatePerson(Person person) throws PersonNotFoundException {		
		if(persons.getPersonsByFirstNameAndLastName(person.getFirstName(), person.getLastName()).isEmpty()) {
			throw new PersonNotFoundException(person.getFirstName() + " " + person.getLastName() + " not found");
		} else {
			return persons.update(person);	
		}
	}

	public void deletePerson(String firstName, String lastName) throws PersonNotFoundException {
		if(persons.getPersonsByFirstNameAndLastName(firstName, lastName).isEmpty()) {
			throw new PersonNotFoundException(firstName + " " + lastName + " not found");
		} else {
			persons.deletePerson(firstName, lastName);
		}	
	}

	public List<Person> getPersonByLastNameAndAddress(String lastName, String address) throws PersonNotFoundException {
		List<Person> list = persons.getPersonByLastNameAndAddress(lastName, address);
		if(list.isEmpty()) {
			throw new PersonNotFoundException(lastName + " was not found at this address : " + address);
		}
		return list;
	}
}

package com.safetynet.saftynetalerts.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.exception.PersonAlreadyExistsException;
import com.safetynet.saftynetalerts.exception.PersonNotFoundException;
import com.safetynet.saftynetalerts.model.Person;
import com.safetynet.saftynetalerts.repository.PersonsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonService implements IPersonService {
	
	private final PersonsRepository persons;
	
	public PersonService(PersonsRepository persons) {
		this.persons = persons;
	}
	
	public List<Person> getAllPersons() throws PersonNotFoundException {
		List<Person> list = persons.getAllPersons();
		if(list.isEmpty()) {
			log.error("Answer : Nobody found");
			throw new PersonNotFoundException("Nobody found");
		}
		log.debug("Answer : ok, Persons list not empty");
		return list;
	}
	
	public List<Person> getPersonsByLastName(String lastName) throws PersonNotFoundException {
		List<Person> list = persons.getPersonsByLastName(lastName);
		if (list.isEmpty()) {
			log.error("Answer : Nodody named {} was found", lastName);
			throw new PersonNotFoundException(lastName + " was not found");
		}
		log.debug("Answer : Person(s) named {} were found", lastName);
		return list;
	}

	public List<Person> getPersonsByAddress(String address) throws PersonNotFoundException {
		List<Person> list = persons.getPersonsByAddress(address);
		if(list.isEmpty()) {
			log.error("Answer : Nobody found at this address : {}", address);
			throw new PersonNotFoundException("Nobody found at this address : " + address);
		}
		log.debug("Answer : Persons found at this address : {}", address);
		return list;
	}

	public List<Person> getPersonsByFirstNameAndLastName(String firstName, String lastName) throws PersonNotFoundException {
		List<Person> list = persons.getPersonsByFirstNameAndLastName(firstName, lastName);
		if(list.isEmpty()) {
			log.error("Answer : Nobody named {} {} was found", firstName, lastName);
			throw new PersonNotFoundException(firstName + " " + lastName + " was not found");
		}
		log.debug("Answer : Person(s) named {} {} were found", firstName, lastName);
		return list;
	}

	// Quelles erreurs ? 
	public Set<String> getCommunityEmail(String city) {
		return persons.getCommunityEmail(city);
	}

	public Person savePerson(Person person) throws PersonAlreadyExistsException {	
		if(!persons.getPersonsByFirstNameAndLastName(person.getFirstName(), person.getLastName()).isEmpty()) {
			log.error("Answer : The person named {} {} already exists", person.getFirstName(), person.getLastName());
			throw new PersonAlreadyExistsException(person.getFirstName() + " " + person.getLastName() + " already exists");
		} else {
			log.info("Answer : The person named {} {} was saved", person.getFirstName(), person.getLastName());
			return persons.save(person);		
		}		
	}

	public Person updatePerson(Person person) throws PersonNotFoundException {		
		if(persons.getPersonsByFirstNameAndLastName(person.getFirstName(), person.getLastName()).isEmpty()) {
			log.error("Answer : The person named {} {} was not found", person.getFirstName(), person.getLastName());
			throw new PersonNotFoundException(person.getFirstName() + " " + person.getLastName() + " not found");
		} else {
			log.info("Answer : The person named {} {} was updated", person.getFirstName(), person.getLastName());
			return persons.update(person);	
		}
	}

	public void deletePerson(String firstName, String lastName) throws PersonNotFoundException {
		if(persons.getPersonsByFirstNameAndLastName(firstName, lastName).isEmpty()) {
			log.error("Answer : The person named {} {} was not found", firstName, lastName);
			throw new PersonNotFoundException(firstName + " " + lastName + " not found");
		} else {
			log.info("Answer : The person named {} {} was deleted", firstName, lastName);
			persons.deletePerson(firstName, lastName);
		}	
	}

	public List<Person> getPersonsByLastNameAndAddress(String lastName, String address) throws PersonNotFoundException {
		List<Person> list = persons.getPersonsByLastNameAndAddress(lastName, address);
		if(list.isEmpty()) {
			log.error("Answer : Nobody named {} was not found at this address : {}", lastName, address);
			throw new PersonNotFoundException(lastName + " was not found at this address : " + address);
		}
		log.debug("Answer : Person(s) named {} were found at this address : {}", lastName, address);
		return list;
	}
}

package com.safetynet.safetynetalerts.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.exception.PersonAlreadyExistsException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.PersonsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonService implements IPersonService {
	
	private final PersonsRepository persons;
	
	public PersonService(PersonsRepository persons) {
		this.persons = persons;
	}
	
	/**
	 * Returns the list of all persons
	 * @return the list of all persons
	 * @throws PersonNotFoundException if no person was found in the respository
	 */
	@Override
	public List<Person> getAllPersons() throws PersonNotFoundException {
		List<Person> list = persons.getAllPersons();
		if(list.isEmpty()) {
			log.error("Answer : Nobody found");
			throw new PersonNotFoundException("Nobody found");
		}
		log.debug("Answer : ok, Persons list not empty");
		return list;
	}
	
	/**
	 * Returns the list of persons whose lastname is given in parameter.
	 * @param lastName
	 * @return 	the list of persons whose lastname is given in parameter.
	 * @throws PersonNotFoundException if no person with this lastname was found in the repository
	 */
	@Override
	public List<Person> getPersonsByLastName(String lastName) throws PersonNotFoundException {
		List<Person> list = persons.getPersonsByLastName(lastName);
		if (list.isEmpty()) {
			log.error("Answer : Nodody named {} was found", lastName);
			throw new PersonNotFoundException(lastName + " was not found");
		}
		log.debug("Answer : Person(s) named {} were found", lastName);
		return list;
	}

	/**
	 * Returns a list of persons whose address is the one given in parameter.
	 * @param address
	 * @return a list of persons whose address is the one given in parameter
	 * @throws PersonNotFoundException if no person was found at this address in the repository
	 */
	@Override
	public List<Person> getPersonsByAddress(String address) throws PersonNotFoundException {
		List<Person> list = persons.getPersonsByAddress(address);
		if(list.isEmpty()) {
			log.error("Answer : Nobody found at this address : {}", address);
			throw new PersonNotFoundException("Nobody found at this address : " + address);
		}
		log.debug("Answer : Persons found at this address : {}", address);
		return list;
	}

	/**
	 * Returns a list of persons whose firstname and lastname are given in parameter.
	 * @param firstName
	 * @param lastName
	 * @return a list of persons whose firstname and lastname are given in parameter or an empty list if no person was found 
	 * @throws PersonNotFoundException if no person was found in the repository with these firstname and lastname
	 */
	@Override
	public List<Person> getPersonsByFirstNameAndLastName(String firstName, String lastName) throws PersonNotFoundException {
		List<Person> list = persons.getPersonsByFirstNameAndLastName(firstName, lastName);
		if(list.isEmpty()) {
			log.error("Answer : Nobody named {} {} was found", firstName, lastName);
			throw new PersonNotFoundException(firstName + " " + lastName + " was not found");
		}
		log.debug("Answer : Person(s) named {} {} were found", firstName, lastName);
		return list;
	}

	/**
	 * Returns a list of email of the persons living in a city given in parameter.
	 * @param city
	 * @return a set of email of the persons living in a city given in parameter or an empty set if no person was found in the repository
	 */
	@Override
	public Set<String> getCommunityEmail(String city) {
		return persons.getCommunityEmail(city);
	}

	/**
	 * Saves in the repository the person given in parameter.
	 * @param person
	 * @return the person saved
	 * @throws PersonAlreadyExistsException if a person already exists in the repository with the same firstname and lastname
	 */
	@Override
	public Person savePerson(Person person) throws PersonAlreadyExistsException {	
		if(!persons.getPersonsByFirstNameAndLastName(person.getFirstName(), person.getLastName()).isEmpty()) {
			log.error("Answer : The person named {} {} already exists", person.getFirstName(), person.getLastName());
			throw new PersonAlreadyExistsException(person.getFirstName() + " " + person.getLastName() + " already exists");
		} else {
			log.info("Answer : The person named {} {} was saved", person.getFirstName(), person.getLastName());
			return persons.save(person);		
		}		
	}

	/**
	 * Updates in the repository the person whose firstname and lastname are the same of the person given in parameter. 
	 * @param person
	 * @return the person updated
	 * @throws PersonNotFoundException if no person was found in the repository with these firstname and lastname
	 */
	@Override
	public Person updatePerson(Person person) throws PersonNotFoundException {		
		if(persons.getPersonsByFirstNameAndLastName(person.getFirstName(), person.getLastName()).isEmpty()) {
			log.error("Answer : The person named {} {} was not found", person.getFirstName(), person.getLastName());
			throw new PersonNotFoundException(person.getFirstName() + " " + person.getLastName() + " not found");
		} else {
			log.info("Answer : The person named {} {} was updated", person.getFirstName(), person.getLastName());
			return persons.update(person);	
		}
	}

	/**
	 * Deletes in the repository the person whose firstname and lastname are given in parameter.
	 * @param firstName
	 * @param lastName
	 * @throws PersonNotFoundException if no person was found in the repository with these firstname and lastname
	 */
	@Override
	public void deletePerson(String firstName, String lastName) throws PersonNotFoundException {
		if(persons.getPersonsByFirstNameAndLastName(firstName, lastName).isEmpty()) {
			log.error("Answer : The person named {} {} was not found", firstName, lastName);
			throw new PersonNotFoundException(firstName + " " + lastName + " not found");
		} else {
			log.info("Answer : The person named {} {} was deleted", firstName, lastName);
			persons.deletePerson(firstName, lastName);
		}	
	}

	/**
	 * Returns a list of persons whose lastname and address are given in parameter.
	 * @param lastName
	 * @param address
	 * @return 	a list of persons whose lastname and address are given in parameter.
	 * @throws PersonNotFoundException if no person was found in the repository with these lastname and address
	 */
	@Override
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

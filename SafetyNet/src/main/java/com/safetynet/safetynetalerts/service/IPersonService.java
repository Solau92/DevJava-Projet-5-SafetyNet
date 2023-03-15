package com.safetynet.safetynetalerts.service;

import java.util.List;
import java.util.Set;

import com.safetynet.safetynetalerts.exception.PersonAlreadyExistsException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.Person;

public interface IPersonService {

	/**
	 * Returns the list of all persons
	 * @return the list of all persons
	 * @throws PersonNotFoundException if no person was found 
	 */
	public List<Person> getAllPersons() throws PersonNotFoundException;
	
	/**
	 * Returns the list of persons whose lastname is given in parameter.
	 * @param lastName
	 * @return 	the list of persons whose lastname is given in parameter.
	 * @throws PersonNotFoundException if no person with this lastname was found 
	 */
	public List<Person> getPersonsByLastName(String lastName) throws PersonNotFoundException;

	/**
	 * Returns a list of persons whose address is the one given in parameter.
	 * @param address
	 * @return a list of persons whose address is the one given in parameter
	 * @throws PersonNotFoundException if no person was found at this address
	 */
	public List<Person> getPersonsByAddress(String address) throws PersonNotFoundException;

	/**
	 * Returns a list of persons whose firstname and lastname are given in parameter.
	 * @param firstName
	 * @param lastName
	 * @return a list of persons whose firstname and lastname are given in parameter or an empty list if no person was found 
	 * @throws PersonNotFoundException if no person was found with these firstname and lastname
	 */
	public List<Person> getPersonsByFirstNameAndLastName(String firstName, String lastName) throws PersonNotFoundException;

	/**
	 * Returns a list of email of the persons living in a city given in parameter.
	 * @param city
	 * @return a set of email of the persons living in a city given in parameter  or an empty set if no person was found
	 */
	public Set<String> getCommunityEmail(String city);

	/**
	 * Saves the person given in parameter.
	 * @param person
	 * @return the person saved
	 * @throws PersonAlreadyExistsException if a person already exists in data source with the same firstname and lastname
	 */
	public Person savePerson(Person person) throws PersonAlreadyExistsException;

	/**
	 * Updates the person whose firstname and lastname are the same of the person given in parameter. 
	 * @param person
	 * @return the person updated
	 * @throws PersonNotFoundException if no person was found with these firstname and lastname
	 */
	public Person updatePerson(Person person) throws PersonNotFoundException;

	/**
	 * Deletes the person whose firstname and lastname are given in parameter.
	 * @param firstName
	 * @param lastName
	 * @throws PersonNotFoundException if no person was found with these firstname and lastname
	 */
	public void deletePerson(String firstName, String lastName) throws PersonNotFoundException;

	/**
	 * Returns a list of persons whose lastname and address are given in parameter.
	 * @param lastName
	 * @param address
	 * @return 	a list of persons whose lastname and address are given in parameter.
	 * @throws PersonNotFoundException if no person was found with these lastname and address
	 */
	List<Person> getPersonsByLastNameAndAddress(String lastName, String address) throws PersonNotFoundException;

}

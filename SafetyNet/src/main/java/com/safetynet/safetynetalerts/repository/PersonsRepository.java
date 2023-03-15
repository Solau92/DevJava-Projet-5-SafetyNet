package com.safetynet.safetynetalerts.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.model.Person;

import lombok.Data;

@Repository
@Data
public class PersonsRepository {

	private List<Person> persons;

	/**
	 * Returns the list of all persons in repository
	 * @return the list of all persons in repository
	 */
	public List<Person> getAllPersons() {
		return persons;
	}

	/**
	 * Returns a list of persons whose lastname is given in parameter.
	 * @param lastName
	 * @return a list of persons whose lastname is given in parameter or an empty list if no person was found
	 */
	public List<Person> getPersonsByLastName(String lastName) {
		List<Person> personsByName = new ArrayList<>();
		for (Person p : persons) {
			if (p.getLastName().equalsIgnoreCase(lastName)) {
				personsByName.add(p);
			}
		}
		return personsByName;
	}

	/**
	 * Returns a list of persons whose address is given in parameter.
	 * @param address
	 * @return a list of persons whose address is given in parameter or an empty list if no person was found 
	 */
	public List<Person> getPersonsByAddress(String address) {
		List<Person> inhabitants = new ArrayList<>();
		for (Person p : persons) {
			if (p.getAddress().equalsIgnoreCase(address)) {
				inhabitants.add(p);
			}
		}
		return inhabitants;
	}

	/**
	 * Returns a list of persons whose firstname and lastname are given in parameter.
	 * @param firstName
	 * @param lastName
	 * @return a list of persons whose firstname and lastname are given in parameter or an empty list if no person was found 
	 */
	public List<Person> getPersonsByFirstNameAndLastName(String firstName, String lastName) {
		List<Person> personsByFirstNameAndLastName = new ArrayList<>();
		for (Person p : persons) {
			if (p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName)) {
				personsByFirstNameAndLastName.add(p);
			}
		}
		return personsByFirstNameAndLastName;
	}

	/**
	 * Returns a list of email of the persons living in a city given in parameter.
	 * @param city
	 * @return a set of email of the persons living in a city given in parameter  or an empty set if no person was found
	 */
	public Set<String> getCommunityEmail(String city) {
		Set<String> communityEmail = new HashSet<>();
		for (Person p : persons) {
			if (p.getCity().equalsIgnoreCase(city)) {
				communityEmail.add(p.getEmail());
			}
		}
		return communityEmail;
	}

	/**
	 * Saves in the repository the person given in parameter.
	 * @param person
	 * @return the person saved
	 */
	public Person save(Person person) {
		persons.add(person);
		return person;
	}

	/**
	 * Updates in the repository the person whose firstname and lastname are the same of the person given in parameter. 
	 * @param person
	 * @return the person updated
	 */
	public Person update(Person person) {
		for (int i = 0 ; i < persons.size() ; i++) {
			if (persons.get(i).getFirstName().equalsIgnoreCase(person.getFirstName()) && persons.get(i).getLastName().equalsIgnoreCase(person.getLastName())) {
				persons.remove(persons.get(i));
			}
		}
			persons.add(person);
			return person;
	}

	/**
	 * Deletes in the repository the person whose firstname and lastname are given in parameter.
	 * @param firstName
	 * @param lastName
	 */
	public void deletePerson(String firstName, String lastName) {
		for (int i = 0 ; i < persons.size() ; i++) {
			if (persons.get(i).getFirstName().equalsIgnoreCase(firstName) && persons.get(i).getLastName().equalsIgnoreCase(lastName)) {
				persons.remove(persons.get(i));
			}
		}		
	}

	/**
	 * Returns a list of persons whose lastname and address are given in parameter.
	 * @param lastName
	 * @param address
	 * @return a list of persons whose lastname and address are given in parameter or an empty list if no person was found
	 */
	public List<Person> getPersonsByLastNameAndAddress(String lastName, String address) {
		List<Person> personsByFirstNameAndAddress = new ArrayList<>();
		for (Person p : persons) {
			if (p.getLastName().equalsIgnoreCase(lastName) && p.getAddress().equalsIgnoreCase(address)) {
				personsByFirstNameAndAddress.add(p);
			}
		}
		return personsByFirstNameAndAddress;
	}

}

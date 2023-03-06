package com.safetynet.saftynetalerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.saftynetalerts.model.Person;

import lombok.Data;

@Repository
@Data
public class PersonsRepository {

	private List<Person> persons;

	public List<Person> getAllPersons() {
		return persons;
	}

	public List<Person> getPersonsByLastName(String lastName) {
		List<Person> personsByName = new ArrayList<Person>();
		for (Person p : persons) {
			if (p.getLastName().equals(lastName)) {
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

	public Person save(Person person) {
		persons.add(person);
		return person;
	}

	public Person update(Person person) {
		for (int i = 0 ; i < persons.size() ; i++) {
			if (persons.get(i).getFirstName().equalsIgnoreCase(person.getFirstName()) && persons.get(i).getLastName().equalsIgnoreCase(person.getLastName())) {
				persons.remove(persons.get(i));
			}
		}
			persons.add(person);
			return person;
	}

	public void deletePerson(String firstName, String lastName) {
		for (int i = 0 ; i < persons.size() ; i++) {
			if (persons.get(i).getFirstName().equalsIgnoreCase(firstName) && persons.get(i).getLastName().equalsIgnoreCase(lastName)) {
				persons.remove(persons.get(i));
			}
		}		
	}

	public List<Person> getPersonByLastNameAndAddress(String lastName, String address) {
		List<Person> personsByFirstNameAndAddress = new ArrayList<Person>();
		for (Person p : persons) {
			if (p.getLastName().equalsIgnoreCase(lastName) && p.getAddress().equalsIgnoreCase(address)) {
				personsByFirstNameAndAddress.add(p);
			}
		}
		return personsByFirstNameAndAddress;
	}

}

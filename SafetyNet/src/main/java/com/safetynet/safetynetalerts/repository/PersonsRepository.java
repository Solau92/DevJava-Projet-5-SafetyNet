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

	public List<Person> getAllPersons() {
		return persons;
	}

	public List<Person> getPersonsByLastName(String lastName) {
		List<Person> personsByName = new ArrayList<>();
		for (Person p : persons) {
			if (p.getLastName().equalsIgnoreCase(lastName)) {
				personsByName.add(p);
			}
		}
		return personsByName;
	}

	public List<Person> getPersonsByAddress(String address) {
		List<Person> inhabitants = new ArrayList<>();
		for (Person p : persons) {
			if (p.getAddress().equalsIgnoreCase(address)) {
				inhabitants.add(p);
			}
		}
		return inhabitants;
	}

	public List<Person> getPersonsByFirstNameAndLastName(String firstName, String lastName) {
		List<Person> personsByFirstNameAndLastName = new ArrayList<>();
		for (Person p : persons) {
			if (p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName)) {
				personsByFirstNameAndLastName.add(p);
			}
		}
		return personsByFirstNameAndLastName;
	}

	public Set<String> getCommunityEmail(String city) {
		Set<String> communityEmail = new HashSet<>();
		for (Person p : persons) {
			if (p.getCity().equalsIgnoreCase(city)) {
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

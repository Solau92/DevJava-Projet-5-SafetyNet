package com.safetynet.safetynetalerts.repository;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.safetynetalerts.model.Person;

@ExtendWith(MockitoExtension.class)
class PersonsRepositoryTest {
	
	@InjectMocks
	private PersonsRepository personRepository;
	
	private static List<Person> persons = new ArrayList<>();
	
	@BeforeEach
	public void setUp() {
		Person person1 = new Person();
		person1.setFirstName("firstName1");
		person1.setLastName("lastName1");
		person1.setAddress("address1");
		person1.setCity("city");
		person1.setEmail("email1");
		Person person2 = new Person();
		person2.setFirstName("firstName2");
		person2.setLastName("lastName2");
		person2.setAddress("address2");
		person2.setCity("city");
		person2.setEmail("email2");
		Collections.addAll(persons, person1, person2);
		personRepository.setPersons(persons);
	}
	
	@AfterEach
	public void tearDown() {
		persons = new ArrayList<>();
	}
	
	@Test
	void getAllPersons_Success_Test() {
		
		// GIVEN 

		// WHEN 
		List<Person> list = personRepository.getAllPersons();
		
		// THEN 
		assertEquals(persons, list);
	}
	
	@Test
	void getPersonsByLastName_Success_Test() {
		
		// GIVEN 
		
		// WHEN 
		List<Person> list = personRepository.getPersonsByLastName("lastName2");

		// THEN 
		assertEquals(persons.get(1), list.get(0));	
	}
	
	@Test
	void getPersonsByAddress_Success_Test() {
		
		// GIVEN 
		
		// WHEN 
		List<Person> list = personRepository.getPersonsByAddress("address2");

		// THEN 
		assertEquals(persons.get(1), list.get(0));	
	}
	
	@Test
	void getPersonsByAddress_NoResult_Test() {
		
		// GIVEN 
		
		// WHEN 
		List<Person> list = personRepository.getPersonsByAddress("address5");

		// THEN 
		assertEquals(0, list.size());	
	}
	
	
	@Test
	void getPersonsByFirstNameAndLastName_Success_Test() {
		
		// GIVEN 
		
		// WHEN 
		List<Person> list = personRepository.getPersonsByFirstNameAndLastName("firstName1", "lastName1");

		// THEN 
		assertEquals(persons.get(0), list.get(0));	
	}

	@Test
	void getPersonsByFirstNameAndLastName_NoResult_Test() {
		
		// GIVEN 
		
		// WHEN 
		List<Person> list = personRepository.getPersonsByFirstNameAndLastName("firstName1", "lastName2");

		// THEN 
		assertEquals(0, list.size());	
	}
	
	@Test
	void getCommunityEmail_Success_Test() {
		
		// GIVEN 
		
		// WHEN 
		Set<String> list = personRepository.getCommunityEmail("city");

		// THEN 
		assertTrue(list.contains("email1"));
		assertTrue(list.contains("email2"));	

	}
	
	@Test
	void getCommunityEmail_NoResult_Test() {
		
		// GIVEN 
		
		// WHEN 
		Set<String> list = personRepository.getCommunityEmail("ville");

		// THEN 
		assertEquals(0, list.size());	
	}
	
	@Test
	void save_Success_Test() {
		
		// GIVEN 
		Person person3 = new Person();
		person3.setFirstName("firstName3");
		person3.setLastName("lastName3");

		// WHEN 
		Person personSaved = personRepository.save(person3);
		
		// THEN 
		assertEquals(personSaved, persons.get(2));
	}
	
	@Test
	void update_Succcess_Test() {
		
		// GIVEN 
		Person person3 = new Person();
		person3.setFirstName("firstName2");
		person3.setLastName("lastName2");
		person3.setEmail("newEmail2");

		// WHEN 
		Person personUpdated = personRepository.update(person3);
		
		// THEN 
		assertEquals(personUpdated, persons.get(1));
		assertEquals("newEmail2", personUpdated.getEmail());
	}
	
	@Test
	void update_Fail_Test() {
		
		// GIVEN 
		Person person3 = new Person();
		person3.setFirstName("firstName");
		person3.setLastName("lastName1");
		person3.setEmail("newEmail1");

		// WHEN 
		Person personUpdated = personRepository.update(person3);
		
		// THEN 
		assertNotEquals(personUpdated, persons.get(0));
	}
	
	@Test
	void update_Fail2_Test() {
		
		// GIVEN 
		Person person3 = new Person();
		person3.setFirstName("firstName1");
		person3.setLastName("lastName");
		person3.setEmail("newEmail1");

		// WHEN 
		Person personUpdated = personRepository.update(person3);
		
		// THEN 
		assertNotEquals(personUpdated, persons.get(0));
	}
	
	@Test
	void delete_Success_Test() {
		
		// WHEN 
		personRepository.deletePerson("firstName1", "lastName1");
		
		// THEN 
		assertEquals(1, persons.size());	
	}
	
	@Test
	void delete_Fail_Test() {
		
		// WHEN 
		personRepository.deletePerson("firstName1", "lastName2");
		
		// THEN 
		assertEquals(2, persons.size());	
	}
	
	@Test
	void getPersonsByLastNameAndAddress_Success_Test() {
		
		// GIVEN 
		
		// WHEN 
		List<Person> list = personRepository.getPersonsByLastNameAndAddress("lastName1", "address1");

		// THEN 
		assertEquals(persons.get(0), list.get(0));	
	}

	@Test
	void getPersonsByLastNameAndAddress_NoResult_Test() {
		
		// GIVEN 
		
		// WHEN 
		List<Person> list = personRepository.getPersonsByLastNameAndAddress("lastName1", "address");

		// THEN 
		assertEquals(0, list.size());	
	}
	

	
}

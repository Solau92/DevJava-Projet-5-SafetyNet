package com.safetynet.saftynetalerts.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.safetynet.saftynetalerts.exception.PersonAlreadyExistsException;
import com.safetynet.saftynetalerts.exception.PersonNotFoundException;
import com.safetynet.saftynetalerts.model.Person;
import com.safetynet.saftynetalerts.service.IPersonService;

@SpringBootTest
public class PersonControllerTest {
	
	@InjectMocks
	private PersonsController personController;
	
	@Mock
	private IPersonService personService;
	
	private static List<Person> listMock = new ArrayList<>();
	private static Person personTest = new Person();
	
	@BeforeAll
	public static void setUp() {
		personTest.setFirstName("firstName");
		personTest.setLastName("lastName");		
		personTest.setAddress("address");
		personTest.setCity("city");
		personTest.setEmail("email");
		Collections.addAll(listMock, personTest);
	}
	
	@Test
	void getAllPersons_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(personService.getAllPersons()).thenReturn(listMock);
		
		// WHEN 
		ResponseEntity<List<Person>> result = personController.getPersons();
		
		// THEN 
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		assertEquals(listMock, result.getBody());
	}

	@Test
	void createPerson_Test() throws PersonAlreadyExistsException {
		
		// GIVEN
		when(personService.savePerson(any(Person.class))).thenReturn(personTest);
		
		// WHEN 
		ResponseEntity<Person> result = personController.createPerson(new Person());
		
		// THEN 
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}
	
	@Test
	void updatePerson_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(personService.updatePerson(any(Person.class))).thenReturn(personTest);
		
		// WHEN 
		ResponseEntity<Person> result = personController.updatePerson(personTest);
		
		// THEN 
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
	}
	
	@Test
	void deletePerson_Test() throws PersonNotFoundException {
		
		// GIVEN
		
		// WHEN 
		ResponseEntity<Void> result = personController.deletePerson(personTest.getFirstName(), personTest.getLastName());
		
		// THEN 
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
	}
	
	@Test
	void getPersonByLastName_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(personService.getPersonsByLastName(anyString())).thenReturn(listMock);
		
		// WHEN 
		ResponseEntity<List<Person>> result = personController.getPersonsByLastName(personTest.getLastName());

		// THEN 
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		assertEquals(listMock, result.getBody());

	}
	
	@Test
	void getPersonByAddress_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(personService.getPersonsByAddress(anyString())).thenReturn(listMock);
		
		// WHEN 
		ResponseEntity<List<Person>> result = personController.getPersonsByAddress(personTest.getAddress());

		// THEN 
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		assertEquals(listMock, result.getBody());
	}
	
	@Test
	void getCommunityEmail_Test() throws PersonNotFoundException {
			
		// GIVEN
		Set<String> list = new HashSet<>();
		list.add("email");

		when(personService.getCommunityEmail(anyString())).thenReturn(list);
		
		// WHEN 
		ResponseEntity<Set<String>> result = personController.getCommunityEmail("city");
		System.out.println(result.getBody().toString());

		// THEN 
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		assertTrue(result.getBody().contains("email"));
	}
	
}

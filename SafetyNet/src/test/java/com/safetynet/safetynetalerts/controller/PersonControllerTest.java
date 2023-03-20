package com.safetynet.safetynetalerts.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
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

import com.safetynet.safetynetalerts.exception.PersonAlreadyExistsException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.IPersonService;

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
	void getAllPersons_Ok_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(personService.getAllPersons()).thenReturn(listMock);
		
		// WHEN 
		ResponseEntity<List<Person>> result = personController.getPersons();
		
		// THEN 
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		assertEquals(listMock, result.getBody());
	}

	@Test
	void getAllPersons_NotFound_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(personService.getAllPersons()).thenThrow(PersonNotFoundException.class);
		
		// WHEN 		
		// THEN 
		assertThrows(PersonNotFoundException.class, ()->personController.getPersons());
	}

	@Test
	void createPerson_Ok_Test() throws PersonAlreadyExistsException {
		
		// GIVEN
		when(personService.savePerson(any(Person.class))).thenReturn(personTest);
		
		// WHEN 
		ResponseEntity<Person> result = personController.createPerson(new Person());
		
		// THEN 
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

	@Test
	void createPerson_AlreadyExists_Test() throws PersonAlreadyExistsException {
		
		// GIVEN
		when(personService.savePerson(any(Person.class))).thenThrow(PersonAlreadyExistsException.class);
		
		// WHEN 
		// THEN 
		assertThrows(PersonAlreadyExistsException.class, ()->personController.createPerson(new Person()));
	}

	@Test
	void updatePerson_Ok_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(personService.updatePerson(any(Person.class))).thenReturn(personTest);
		
		// WHEN 
		ResponseEntity<Person> result = personController.updatePerson(personTest);
		
		// THEN 
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
	}

	@Test
	void updatePerson_NotFound_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(personService.updatePerson(any(Person.class))).thenThrow(PersonNotFoundException.class);
		
		// WHEN 		
		// THEN 
		assertThrows(PersonNotFoundException.class, ()->personController.updatePerson(personTest));
	}

	@Test
	void deletePerson_Ok_Test() throws PersonNotFoundException {

		// GIVEN

		// WHEN
		ResponseEntity<Void> result = personController.deletePerson(personTest.getFirstName(),
				personTest.getLastName());

		// THEN
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
	}

	@Test
	void deletePerson_NotFound_Test() throws PersonNotFoundException {

		// GIVEN
		doThrow(PersonNotFoundException.class).when(personService).deletePerson(anyString(), anyString());
		// WHEN

		// THEN
		assertThrows(PersonNotFoundException.class,
				() -> personController.deletePerson("notFoundFirstName", "notFoundfalseLastName"));
	}

	@Test
	void getPersonByLastName_Ok_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(personService.getPersonsByLastName(anyString())).thenReturn(listMock);
		
		// WHEN 
		ResponseEntity<List<Person>> result = personController.getPersonsByLastName(personTest.getLastName());

		// THEN 
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		assertEquals(listMock, result.getBody());
	}

	@Test
	void getPersonByLastName_NotFound_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(personService.getPersonsByLastName(anyString())).thenThrow(PersonNotFoundException.class);
		
		// WHEN 
		// THEN 
		assertThrows(PersonNotFoundException.class, ()->personController.getPersonsByLastName("notFoundLastName"));
	}

	@Test
	void getPersonByAddress_Ok_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(personService.getPersonsByAddress(anyString())).thenReturn(listMock);
		
		// WHEN 
		ResponseEntity<List<Person>> result = personController.getPersonsByAddress(personTest.getAddress());

		// THEN 
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		assertEquals(listMock, result.getBody());
	}
	
	@Test
	void getPersonByAddress_NotFound_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(personService.getPersonsByAddress(anyString())).thenThrow(PersonNotFoundException.class);
		
		// WHEN 
		// THEN 
		assertThrows(PersonNotFoundException.class, ()->personController.getPersonsByAddress(personTest.getAddress()));
	}

	@Test
	void getCommunityEmail_Ok_Test() throws PersonNotFoundException {

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

package com.safetynet.safetynetalerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.safetynetalerts.exception.PersonAlreadyExistsException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.PersonsRepository;
import com.safetynet.safetynetalerts.service.PersonService;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

	@InjectMocks
	private PersonService personService; 
	
	@Mock 
	private PersonsRepository persons;
	
	private static List<Person> listMock = new ArrayList<>();
	private static Person personTest = new Person();
	
	@BeforeAll
	public static void setUp() {
		personTest.setFirstName("firstName");
		personTest.setLastName("lastName");		
		personTest.setAddress("address");
		Collections.addAll(listMock, personTest);
	}
	
	@Test
	void getAllPersons_Succes_Test() throws PersonNotFoundException {
		
		//GIVEN 
		when(persons.getAllPersons()).thenReturn(listMock);
		
		//WHEN 
		List<Person> list = personService.getAllPersons();
		
		//THEN 
		verify(persons, Mockito.times(1)).getAllPersons();
		assertEquals(personTest, list.get(0));
	}
	
	@Test
	void getAllPersons_NoResult_Test() {
		
		//GIVEN 
		when(persons.getAllPersons()).thenReturn(new ArrayList<>());
		
		//WHEN 
		PersonNotFoundException result = assertThrows(PersonNotFoundException.class, () -> personService.getAllPersons());
		
		//THEN 
		assertEquals("Nobody found", result.getMessage());
	}
	
	@Test
	void getPersonsByLastName_Success_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(persons.getPersonsByLastName(anyString())).thenReturn(listMock);
		
		// WHEN 
		List<Person> list = personService.getPersonsByLastName("lastName");
		
		// THEN 
		verify(persons, Mockito.times(1)).getPersonsByLastName(anyString());
		assertEquals(personTest, list.get(0));
	}
	
	@Test
	void getPersonsByLastName_NoResult_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(persons.getPersonsByLastName(anyString())).thenReturn(new ArrayList<>());
		
		// WHEN 
		PersonNotFoundException result = assertThrows(PersonNotFoundException.class, () -> personService.getPersonsByLastName("lastName"));
		
		// THEN 
		assertEquals("lastName was not found", result.getMessage());
	}
	
	
	@Test
	void getPersonsByLastAddress_Success_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(persons.getPersonsByAddress(anyString())).thenReturn(listMock);
		
		// WHEN 
		List<Person> list = personService.getPersonsByAddress("lastName");
		
		// THEN 
		verify(persons, Mockito.times(1)).getPersonsByAddress(anyString());
		assertEquals(personTest, list.get(0));
	}
	
	@Test
	void getPersonsByAddress_NoResult_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(persons.getPersonsByAddress(anyString())).thenReturn(new ArrayList<>());
		
		// WHEN 
		PersonNotFoundException result = assertThrows(PersonNotFoundException.class, () -> personService.getPersonsByAddress("address"));
		
		// THEN 
		assertEquals("Nobody found at this address : address", result.getMessage());
	}
	
	@Test
	void getPersonsByFirstNameAndLastName_Success_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(persons.getPersonsByFirstNameAndLastName(anyString(), anyString())).thenReturn(listMock);
		
		// WHEN 
		List<Person> list = personService.getPersonsByFirstNameAndLastName("firstName", "lastName");
		
		// THEN 
		verify(persons, Mockito.times(1)).getPersonsByFirstNameAndLastName(anyString(), anyString());
		assertEquals(personTest, list.get(0));
	}
	
	@Test
	void getPersonsByFirstNameAndLastName_NoResult_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(persons.getPersonsByFirstNameAndLastName(anyString(), anyString())).thenReturn(new ArrayList<>());
		
		// WHEN 
		PersonNotFoundException result = assertThrows(PersonNotFoundException.class, () -> personService.getPersonsByFirstNameAndLastName("firstName", "lastName"));
		
		// THEN 
		assertEquals("firstName lastName was not found", result.getMessage());
	}

	@Test
	void getPersonsByLasNameAndAddress_Success_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(persons.getPersonsByLastNameAndAddress(anyString(), anyString())).thenReturn(listMock);
		
		// WHEN 
		List<Person> list = personService.getPersonsByLastNameAndAddress("lastName", "address");
		
		// THEN 
		verify(persons, Mockito.times(1)).getPersonsByLastNameAndAddress(anyString(), anyString());
		assertEquals(personTest, list.get(0));
	}
	
	@Test
	void getPersonsByLasNameAndAddress_NoResult_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(persons.getPersonsByLastNameAndAddress(anyString(), anyString())).thenReturn(new ArrayList<>());
		
		// WHEN 
		PersonNotFoundException result = assertThrows(PersonNotFoundException.class, () -> personService.getPersonsByLastNameAndAddress("lastName", "address"));
		
		// THEN 
		assertEquals("lastName was not found at this address : address", result.getMessage());
	}
	
	
	@Test
	void getCommunityEmail_Success_Test() {
		
		// GIVEN
		Set<String> list = new HashSet<>();
		list.add("email1");
		when(persons.getCommunityEmail(anyString())).thenReturn(list);
		
		// WHEN 
		Set<String> result = personService.getCommunityEmail("city");
		
		// THEN 
		verify(persons, Mockito.times(1)).getCommunityEmail("city");
		assertTrue(result.contains("email1"));
	}
	
	
	@Test
	void savePerson_Success_Test() throws PersonAlreadyExistsException {
		
		// GIVEN
		when(persons.getPersonsByFirstNameAndLastName(anyString(), anyString())).thenReturn(new ArrayList<>());
		when(persons.save(any(Person.class))).thenReturn(personTest);

		// WHEN 
		Person person = personService.savePerson(personTest);
		
		// THEN 
		assertEquals(personTest, person);
	}
	
	@Test
	void savePerson_Fail_Test() throws PersonAlreadyExistsException {
		
		// GIVEN
		when(persons.getPersonsByFirstNameAndLastName(anyString(), anyString())).thenReturn(listMock);

		// WHEN 
		PersonAlreadyExistsException result = assertThrows(PersonAlreadyExistsException.class, () -> personService.savePerson(personTest));
		
		// THEN 
		assertEquals("firstName lastName already exists", result.getMessage());
	}
	
	@Test
	void updatePerson_Success_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(persons.getPersonsByFirstNameAndLastName(anyString(), anyString())).thenReturn(listMock);
		when(persons.update(any(Person.class))).thenReturn(personTest);

		// WHEN 
		Person person = personService.updatePerson(personTest);
		
		// THEN 
		assertEquals(personTest, person);		
	}
	
	@Test
	void updatePerson_Fail_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(persons.getPersonsByFirstNameAndLastName(anyString(), anyString())).thenReturn(new ArrayList<>());

		// WHEN 
		PersonNotFoundException result = assertThrows(PersonNotFoundException.class, () -> personService.updatePerson(personTest));
		
		// THEN 
		assertEquals("firstName lastName not found", result.getMessage());	
	}
	
	@Test
	void deletePerson_Success_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(persons.getPersonsByFirstNameAndLastName(anyString(), anyString())).thenReturn(listMock);

		// WHEN 
		personService.deletePerson("firstName", "lastName");
		
		// THEN 
		verify(persons, Mockito.times(1)).deletePerson("firstName", "lastName");		
	}
	
	@Test
	void deletePerson_Fail_Test() throws PersonNotFoundException {
		
		// GIVEN
		when(persons.getPersonsByFirstNameAndLastName(anyString(), anyString())).thenReturn(new ArrayList<>());

		// WHEN 
		PersonNotFoundException result = assertThrows(PersonNotFoundException.class, () -> personService.deletePerson("firstName", "lastName"));
		
		// THEN 
		assertEquals("firstName lastName not found", result.getMessage());	
	}
	
}

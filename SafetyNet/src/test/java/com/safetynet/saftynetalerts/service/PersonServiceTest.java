package com.safetynet.saftynetalerts.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.saftynetalerts.exception.PersonNotFoundException;
import com.safetynet.saftynetalerts.model.Person;
import com.safetynet.saftynetalerts.repository.PersonsRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PersonServiceTest {

	@Autowired
	private PersonService personService; 
	
	@Mock 
	private PersonsRepository persons;
	
//	@BeforeAll
//	public void setUp() {
//		personService = new PersonService(persons);
//	}
	
	@Test
	void getAllPersons_Succes_Test() throws PersonNotFoundException {
		
		//GIVEN 
		List<Person> listMock = new ArrayList<Person>();
		Person p = new Person();
		p.setFirstName("fName");
		p.setLastName("lName");
		listMock.add(p);
		when(persons.getAllPersons()).thenReturn(listMock);
		
		//WHEN 
		List<Person> list = personService.getAllPersons();
		
		//THEN 
		verify(persons, Mockito.times(1)).getAllPersons();
	}
	
	@Test
	void getAllPersons_NoResult_Test() {
		
		//GIVEN 
		List<Person> listMock = new ArrayList<Person>();
		when(persons.getAllPersons()).thenReturn(listMock);
		
		//WHEN 
		List<Person> list = persons.getAllPersons();
		
		//THEN 
		verify(persons, Mockito.times(1)).getAllPersons();
	}
}

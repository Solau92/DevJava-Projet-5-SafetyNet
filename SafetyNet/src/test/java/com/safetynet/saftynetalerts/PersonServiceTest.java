package com.safetynet.saftynetalerts;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.saftynetalerts.model.Person;
import com.safetynet.saftynetalerts.repository.PersonsRepository;
import com.safetynet.saftynetalerts.service.PersonService;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
//
//	private static PersonService personService; 
//	
//	@Mock 
//	private static PersonsRepository persons;
//	
//	@Test
//	void getAllPersonsTest() {
//				
////		when(persons.getAllPersons()).thenReturn(new ArrayList<Person>());
////		
////		personService = new PersonService();
////		List<Person> liste = personService.getAllPersons();
////		
////		verify(persons, Mockito.times(1)).getAllPersons();
//
//	}
}

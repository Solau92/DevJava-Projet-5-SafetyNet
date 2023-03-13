package com.safetynet.safetynetalerts.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOFirestation;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.IFirestationService;
import com.safetynet.safetynetalerts.service.IMedicalRecordService;
import com.safetynet.safetynetalerts.service.IPersonService;
import com.safetynet.safetynetalerts.service.URLFirestationService;

@ExtendWith(MockitoExtension.class)
class URLFirestationServiceTest {

	@InjectMocks
	private URLFirestationService uRLFirestationService;
	
	@Mock
	private IPersonService personService;
	
	@Mock
	private IMedicalRecordService medicalRecordService;
	
	@Mock
	private IFirestationService firestationService;
	
	@Test
	void getFirestation_Success_Test() throws PersonNotFoundException, MedicalRecordNotFoundException, FirestationNotFoundException {
		
		// GIVEN
		
		List<String> addressesList = new ArrayList<>();
		addressesList.add("address");
		when(firestationService.getAddressesWithId(anyInt())).thenReturn(addressesList);
		
		List<Person> personsList = new ArrayList<>();
		Person personTest1 = new Person();
		personTest1.setFirstName("firstName1");
		personTest1.setLastName("lastName1");
		personTest1.setEmail("email1");
		personsList.add(personTest1);
		Person personTest2 = new Person();
		personTest2.setFirstName("firstName2");
		personTest2.setLastName("lastName2");
		personTest2.setEmail("email2");
		personsList.add(personTest2);
		when(personService.getPersonsByAddress(anyString())).thenReturn(personsList);
		
		when(medicalRecordService.isPersonAdult(anyString(), anyString())).thenReturn(true, false);
		
		// WHEN 
		DTOFirestation dTOFirestation = uRLFirestationService.getFirestation(1);
		
		// THEN 
		assertEquals(1, dTOFirestation.getNumberOfAdults());
		assertEquals(1, dTOFirestation.getNumberOfChildren());
		assertEquals("firstName1", dTOFirestation.getFirestationPersons().get(0).getFirstName());
		assertEquals("lastName2", dTOFirestation.getFirestationPersons().get(1).getLastName());

	}
	
}

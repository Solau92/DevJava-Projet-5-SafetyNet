package com.safetynet.safetynetalerts.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.IFirestationService;
import com.safetynet.safetynetalerts.service.IPersonService;
import com.safetynet.safetynetalerts.service.URLPhoneAlertService;

@ExtendWith(MockitoExtension.class)
class URLPhoneAlertServiceTest {
	
	@InjectMocks
	private URLPhoneAlertService uRLPhoneAlertService; 
	
	@Mock
	private IPersonService personService;
	
	@Mock
	private IFirestationService firestationService;
	
	@Test
	void getPhoneAlert_Success_Test() throws FirestationNotFoundException, PersonNotFoundException {
		
		// GIVEN 		
		List<Person> personList = new ArrayList<>();
		Person personTest = new Person();
		personTest.setFirstName("firstName");
		personTest.setLastName("lastName");
		personTest.setAddress("address");
		personTest.setPhone("012345");
		personList.add(personTest);
		
		List<String> addressesList = new ArrayList<>();
		addressesList.add("address");
		
		when(firestationService.getAddressesWithId(anyInt())).thenReturn(addressesList);
		when(personService.getPersonsByAddress(anyString())).thenReturn(personList);
		
		// WHEN 
		Set<String> phoneList = uRLPhoneAlertService.getPhoneAlert(1);
		
		// THEN 
		assertEquals(1, phoneList.size());
		assertTrue(phoneList.contains("012345"));		
	}
	
	

}

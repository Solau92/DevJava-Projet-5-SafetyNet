package com.safetynet.safetynetalerts.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.safetynet.safetynetalerts.model.DTOFlood;
import com.safetynet.safetynetalerts.model.DTOPersonInfo;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.IFirestationService;
import com.safetynet.safetynetalerts.service.IMedicalRecordService;
import com.safetynet.safetynetalerts.service.IPersonService;
import com.safetynet.safetynetalerts.service.URLFloodService;

@ExtendWith(MockitoExtension.class)
class URLFloodServiceTestTODO {
	
	@InjectMocks
	private URLFloodService uRLFloodService;
	
	@Mock
	private IPersonService personService;
	
	@Mock
	private IMedicalRecordService medicalRecordService;
	
	@Mock 
	private IFirestationService firestationService; 
	
	@Test
	void getFlood_Success_Test() throws PersonNotFoundException, MedicalRecordNotFoundException, FirestationNotFoundException {
		
		// GIVEN 
		
		List<Integer> stationIdList = new ArrayList<>();
		stationIdList.add(1);
		
		List<String> addressesList = new ArrayList<>();
		addressesList.add("address");
		when(firestationService.getAddressesWithId(anyInt())).thenReturn(addressesList);
		
		List<Person> personsList = new ArrayList<>();
		Person personTest1 = new Person();
		personTest1.setFirstName("firstName1");
		personTest1.setLastName("lastName");
		personTest1.setEmail("email1");
		personsList.add(personTest1);
		Person personTest2 = new Person();
		personTest2.setFirstName("firstName");
		personTest2.setLastName("lastName2");
		personTest2.setEmail("email2");
		when(personService.getPersonsByAddress(anyString())).thenReturn(personsList);
		
		List<MedicalRecord> medicalRecordList = new ArrayList<>();
		MedicalRecord medicalRecordTest = new MedicalRecord();
		medicalRecordTest.setFirstName("firstName");
		medicalRecordTest.setLastName("lastName");
		medicalRecordTest.setBirthdate(LocalDate.now().minusYears(30));
		medicalRecordList.add(medicalRecordTest);
		when(medicalRecordService.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(medicalRecordList);
		
		// WHEN 
		List<DTOFlood> dtoFloodList = uRLFloodService.getFlood(stationIdList);
		
		// THEN 
		assertEquals("address", dtoFloodList.get(0).getAddress());
		assertTrue(dtoFloodList.get(0).getFamilyList().containsKey("lastName"));
//		assertTrue(dtoFloodList.get(0).getFamilyList().get(dtoFloodList).contains(personTest2));
		
	}

}

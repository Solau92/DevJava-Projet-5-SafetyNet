package com.safetynet.saftynetalerts.service;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.safetynet.saftynetalerts.exception.FirestationNotFoundException;
import com.safetynet.saftynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.saftynetalerts.exception.PersonNotFoundException;
import com.safetynet.saftynetalerts.model.DTOFire;
import com.safetynet.saftynetalerts.model.DTOPersonInfo;
import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.model.Person;

@ExtendWith(MockitoExtension.class)
public class URLFireServiceTest {

	@InjectMocks
	private URLFireService uRLFireService;
	
	@Mock
	private IPersonService personService;
	
	@Mock
	private IMedicalRecordService medicalRecordService;
	
	@Mock
	private IFirestationService firestationService;
	
	@Test
	void getFire_Success_Test() throws PersonNotFoundException, MedicalRecordNotFoundException, FirestationNotFoundException {
		
		// GIVEN 
		
		List<Person> personsList = new ArrayList<>();
		Person personTest1 = new Person();
		personTest1.setFirstName("firstName1");
		personTest1.setLastName("lastName1");
		personTest1.setAddress("address");
		personsList.add(personTest1);
		Person personTest2 = new Person();
		personTest2.setFirstName("firstName2");
		personTest2.setLastName("lastName2");
		personTest2.setAddress("address");
		personsList.add(personTest2);
		when(personService.getPersonsByAddress(anyString())).thenReturn(personsList);
		
		List<MedicalRecord> medicalRecordList1 = new ArrayList<>();
		MedicalRecord medicalRecordTest1 = new MedicalRecord();
		medicalRecordTest1.setFirstName("firstName1");
		medicalRecordTest1.setLastName("lastName1");
		medicalRecordTest1.setBirthdate(LocalDate.now().minusYears(30));
		medicalRecordList1.add(medicalRecordTest1);
		List<MedicalRecord> medicalRecordList2 = new ArrayList<>();
		MedicalRecord medicalRecordTest2 = new MedicalRecord();
		medicalRecordTest2.setFirstName("firstName2");
		medicalRecordTest2.setLastName("lastName2");
		medicalRecordTest2.setBirthdate(LocalDate.now().minusYears(10));
		medicalRecordList2.add(medicalRecordTest2);
		when(medicalRecordService.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(medicalRecordList1, medicalRecordList2);
		
		// WHEN 
		DTOFire dtoFire = uRLFireService.getFire("address");
		
		// THEN 
		System.out.println(dtoFire.toString());
		assertEquals("lastName1", dtoFire.getPersonsInBuilding().get(0).getLastName());
		assertEquals(10, dtoFire.getPersonsInBuilding().get(1).getAge());
		
	}
	
	
	
	
}

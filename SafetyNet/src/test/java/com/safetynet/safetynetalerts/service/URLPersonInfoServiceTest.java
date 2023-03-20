package com.safetynet.safetynetalerts.service;

import static org.junit.Assert.assertEquals;
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

import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOPersonInfo;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

@ExtendWith(MockitoExtension.class)
class URLPersonInfoServiceTest {
	
	@InjectMocks
	private URLPersonInfoService uRLPersonInfoService;
	
	@Mock
	private IPersonService personService;
	
	@Mock
	private IMedicalRecordService medicalRecordService;
	
	@Test
	void getPersonInfo_Success_Test() throws PersonNotFoundException, MedicalRecordNotFoundException {

		List<Person> personList = new ArrayList<>();
		Person personTest = new Person();
		personTest.setFirstName("firstName");
		personTest.setLastName("lastName");
		personTest.setEmail("email");
		personList.add(personTest);
		
		List<MedicalRecord> medicalRecordList = new ArrayList<>();
		MedicalRecord medicalRecordTest = new MedicalRecord();
		medicalRecordTest.setFirstName("firstName");
		medicalRecordTest.setLastName("lastName");
		medicalRecordTest.setBirthdate(LocalDate.now().minusYears(30));
		medicalRecordList.add(medicalRecordTest);
		
		// GIVEN 
		when(personService.getPersonsByFirstNameAndLastName(anyString(), anyString())).thenReturn(personList);
		when(medicalRecordService.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(medicalRecordList);
		
		// WHEN 
		List<DTOPersonInfo> resultat = uRLPersonInfoService.getPersonInfo("firstName", "lastName");
		
		// THEN 
		assertEquals(1, resultat.size());
		assertEquals("lastName", resultat.get(0).getLastName());
		assertEquals("email", resultat.get(0).getEmail());
		assertEquals(30, resultat.get(0).getAge());	
	}
	
	@Test
	void getPersonInfo_Fail1_Test() throws PersonNotFoundException, MedicalRecordNotFoundException {

		List<Person> personList = new ArrayList<>();
		Person personTest = new Person();
		personTest.setFirstName("firstName");
		personTest.setLastName("lastName2");
		personTest.setEmail("email");
		personList.add(personTest);
		
		List<MedicalRecord> medicalRecordList = new ArrayList<>();
		MedicalRecord medicalRecordTest = new MedicalRecord();
		medicalRecordTest.setFirstName("firstName");
		medicalRecordTest.setLastName("lastName");
		medicalRecordTest.setBirthdate(LocalDate.now().minusYears(30));
		medicalRecordList.add(medicalRecordTest);
		
		// GIVEN 
		when(personService.getPersonsByFirstNameAndLastName(anyString(), anyString())).thenReturn(personList);
		when(medicalRecordService.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(medicalRecordList);
		
		// WHEN 
		List<DTOPersonInfo> resultat = uRLPersonInfoService.getPersonInfo("firstName", "lastName");
		
		// THEN 
		assertEquals(0, resultat.size());
	}
	
	@Test
	void getPersonInfo_Fail2_Test() throws PersonNotFoundException, MedicalRecordNotFoundException {

		List<Person> personList = new ArrayList<>();
		Person personTest = new Person();
		personTest.setFirstName("firstName2");
		personTest.setLastName("lastName2");
		personTest.setEmail("email");
		personList.add(personTest);
		
		List<MedicalRecord> medicalRecordList = new ArrayList<>();
		MedicalRecord medicalRecordTest = new MedicalRecord();
		medicalRecordTest.setFirstName("firstName");
		medicalRecordTest.setLastName("lastName");
		medicalRecordTest.setBirthdate(LocalDate.now().minusYears(30));
		medicalRecordList.add(medicalRecordTest);
		
		// GIVEN 
		when(personService.getPersonsByFirstNameAndLastName(anyString(), anyString())).thenReturn(personList);
		when(medicalRecordService.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(medicalRecordList);
		
		// WHEN 
		List<DTOPersonInfo> resultat = uRLPersonInfoService.getPersonInfo("firstName", "lastName");
		
		// THEN 
		assertEquals(0, resultat.size());
	}

}

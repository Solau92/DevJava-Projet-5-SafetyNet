package com.safetynet.saftynetalerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Fail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.saftynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.saftynetalerts.exception.PersonNotFoundException;
import com.safetynet.saftynetalerts.model.DTOChildAlert;
import com.safetynet.saftynetalerts.model.DTOPersonInfo;
import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.model.Person;

import junit.framework.AssertionFailedError;

@ExtendWith(MockitoExtension.class)
class URLChildAlertServiceTestTODO {
	
//	@InjectMocks
//	private URLChildAlertService uRLChildAlertService;
//	
//	@Mock
//	private IPersonService personService;
//	
//	@Mock
//	private IMedicalRecordService medicalRecordService;
//	
//	@Test
//	void getChildAlert_Success_Test() throws PersonNotFoundException, MedicalRecordNotFoundException {
//		
//		// GIVEN 
//		List<Person> personList = new ArrayList<>();
//		Person personTest1 = new Person();
//		personTest1.setFirstName("firstNameAdult");
//		personTest1.setLastName("lastName");
//		personTest1.setAddress("address");
//		Person personTest2 = new Person();
//		personTest2.setFirstName("firstNameChild");
//		personTest2.setLastName("lastName");
//		personTest2.setAddress("address");
//		personList.add(personTest1);
//		personList.add(personTest2);
//		
//		System.out.println("personList : " + personList.toString());
//		
//		List<MedicalRecord> medicalRecordList1 = new ArrayList<>();
//		MedicalRecord medicalRecordTest1 = new MedicalRecord();
//		medicalRecordTest1.setFirstName("firstNameAdult");
//		medicalRecordTest1.setLastName("lastNameAdult");
//		medicalRecordTest1.setBirthdate(LocalDate.now().minusYears(30));
//		medicalRecordList1.add(medicalRecordTest1);
//		
//		System.out.println("medicalRecordList1 : " + medicalRecordList1.toString());
//		
//		List<MedicalRecord> medicalRecordList2 = new ArrayList<>();
//		MedicalRecord medicalRecordTest2 = new MedicalRecord();
//		medicalRecordTest2.setFirstName("firstNameChild");
//		medicalRecordTest2.setLastName("lastNameChild");
//		medicalRecordTest2.setBirthdate(LocalDate.now().minusYears(10));
//		medicalRecordList2.add(medicalRecordTest2);
//		
//		System.out.println("medicalRecordList2 : " + medicalRecordTest2);
//		
//		when(personService.getPersonsByAddress(anyString())).thenReturn(personList);	
//		
//		when(medicalRecordService.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(medicalRecordList1).thenReturn(medicalRecordList2);
//		when(medicalRecordService.isPersonAdult(anyString(), anyString())).thenReturn(true).thenReturn(false);
//
////		when(medicalRecordService.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(medicalRecordList1);
////		when(medicalRecordService.isPersonAdult(anyString(), anyString())).thenReturn(true);
////		when(medicalRecordService.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(medicalRecordList2);
////		when(medicalRecordService.isPersonAdult(anyString(), anyString())).thenReturn(false);
//		
//		when(personService.getPersonsByLastNameAndAddress(anyString(), anyString())).thenReturn(personList);
//		
//		// WHEN 
//		List<DTOChildAlert> childAlertList = uRLChildAlertService.getChildAlert("address");
//		System.out.println(childAlertList.toString());
//				
//		// THEN 
//		assertEquals("firstNameChild", childAlertList.get(0).getFirstName());
//		assertEquals("firstNameAdult", childAlertList.get(0).getFamilyMembers().get(0).getFirstName());
//	}

}

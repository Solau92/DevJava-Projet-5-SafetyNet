package com.safetynet.saftynetalerts.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.saftynetalerts.model.MedicalRecord;

@ExtendWith(MockitoExtension.class)
class MedicalRecordRepositoryTest {
	
	@InjectMocks
	private MedicalRecordsRepository medicalRecordRepository;
	
	private static List<MedicalRecord> medicalRecords = new ArrayList<>();
	
	@BeforeEach
	public void setUp() {
		MedicalRecord medicalRecordAdult = new MedicalRecord();
		medicalRecordAdult.setFirstName("firstNameAdult");
		medicalRecordAdult.setLastName("lastNameAdult");
		medicalRecordAdult.setBirthdate(LocalDate.now().minusYears(20));
		MedicalRecord medicalRecordChild = new MedicalRecord();
		medicalRecordChild.setFirstName("firstNameChild");
		medicalRecordChild.setLastName("lastNameChild");
		medicalRecordChild.setBirthdate(LocalDate.now().minusYears(1));
		Collections.addAll(medicalRecords, medicalRecordAdult, medicalRecordChild);
		medicalRecordRepository.setMedicalRecords(medicalRecords);
	}
	
	@AfterEach
	public void tearDown() {
		medicalRecords = new ArrayList<>();
	}
	
	@Test
	void getAllMedicalRecords_Success_Test() {
		
		// GIVEN 

		// WHEN 
		List<MedicalRecord> list = medicalRecordRepository.getAllMedicalRecords();
		
		// THEN 
		assertEquals(medicalRecords, list);
	}
	
	@Test
	void save_Success_Test() {
		
		// GIVEN 
		MedicalRecord medicalRecord3 = new MedicalRecord();
		medicalRecord3.setFirstName("firstName3");
		medicalRecord3.setLastName("lastName3");
		medicalRecord3.setBirthdate(LocalDate.now().minusYears(30));

		// WHEN 
		MedicalRecord medicalRecordSaved = medicalRecordRepository.save(medicalRecord3);
		
		// THEN 
		assertEquals(medicalRecordSaved, medicalRecords.get(2));
	}
	
	@Test
	void update_Succcess_Test() {
		
		// GIVEN 
		MedicalRecord medicalRecord3 = new MedicalRecord();
		medicalRecord3.setFirstName("firstNameChild");
		medicalRecord3.setLastName("lastNameChild");
		medicalRecord3.setBirthdate(LocalDate.now().minusYears(2));

		// WHEN 
		MedicalRecord medicalRecordUpdated = medicalRecordRepository.update(medicalRecord3);
		
		// THEN 
		assertEquals(medicalRecordUpdated, medicalRecords.get(1));
		assertEquals(LocalDate.now().minusYears(2), medicalRecordUpdated.getBirthdate());
	}
	
	@Test
	void update_NoResult_Test() {
		
		// GIVEN 
		MedicalRecord medicalRecord3 = new MedicalRecord();
		medicalRecord3.setFirstName("firstNameChild");
		medicalRecord3.setLastName("lastName");
		medicalRecord3.setBirthdate(LocalDate.now().minusYears(2));

		// WHEN 
		MedicalRecord medicalRecordUpdated = medicalRecordRepository.update(medicalRecord3);
		
		// THEN 
		assertEquals(medicalRecordUpdated, medicalRecord3);
		assertEquals(LocalDate.now().minusYears(1), medicalRecords.get(1).getBirthdate());
	}
	
//	@Test
//	void update_NoResult_Test() {
//		
//		// GIVEN 
//		Person person3 = new Person();
//		person3.setFirstName("firstName2");
//		person3.setLastName("lastName3");
//		person3.setEmail("newEmail2");
//
//		// WHEN 
//		Person personUpdated = personRepository.update(person3);
//		
//		// THEN 
//		// ?? 
// 	}
	
	@Test
	void delete_Success_Test() {
		
		// WHEN 
		medicalRecordRepository.deleteMedicalRecord("firstNameChild", "lastNameChild");
		
		// THEN 
		assertEquals(1, medicalRecords.size());	
	}
	
	@Test
	void delete_Fail_Test() {
		
		// WHEN 
		medicalRecordRepository.deleteMedicalRecord("firstName", "lastName");
		
		// THEN 
		assertEquals(2, medicalRecords.size());	
	}
	
	@Test
	void delete_Fail2_Test() {
		
		// WHEN 
		medicalRecordRepository.deleteMedicalRecord("firstNameAdult", "lastName");
		
		// THEN 
		assertEquals(2, medicalRecords.size());	
	}
	
	@Test
	void isPersonAdult_Yes_Test() {
		
		// WHEN 
		boolean result = medicalRecordRepository.isPersonAdult("firstNameAdult", "lastNameAdult");
		
		// THEN
		assertTrue(result);
	}
	
	@Test
	void isPersonAdult_No_Test() {
		
		// WHEN 
		boolean result = medicalRecordRepository.isPersonAdult("firstNameChild", "lastNameChild");
		
		// THEN
		assertFalse(result);
	}
	
	@Test
	void isPersonAdult_NoResult_Test() {
		
		// WHEN 
		boolean result = medicalRecordRepository.isPersonAdult("firstName", "lastName");
		
		// THEN
		assertTrue(result);
	}
	
	@Test
	void isPersonAdult_NoResult2_Test() {
		
		// WHEN 
		boolean result = medicalRecordRepository.isPersonAdult("firstNameChild", "lastName");
		
		// THEN
		assertTrue(result);
	}
	
	@Test
	void getMedicalRecordsByFirstNameAndLastName_Success_Test() {
		
		// GIVEN 
		
		// WHEN 
		List<MedicalRecord> list = medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName("firstNameChild", "lastNameChild");

		// THEN 
		assertEquals(medicalRecords.get(1), list.get(0));	
	}

	@Test
	void getMedicalRecordsByFirstNameAndLastName_NoResult_Test() {
		
		// GIVEN 
		
		// WHEN 
		List<MedicalRecord> list = medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName("firstNameAdult", "lastName");

		// THEN 
		assertEquals(0, list.size());	
	}
	

}

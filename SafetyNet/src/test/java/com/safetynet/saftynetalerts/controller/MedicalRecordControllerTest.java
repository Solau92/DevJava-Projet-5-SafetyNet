package com.safetynet.saftynetalerts.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.safetynet.saftynetalerts.exception.MedicalRecordAlreadyExistsException;
import com.safetynet.saftynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.service.IMedicalRecordService;

@SpringBootTest
public class MedicalRecordControllerTest {
	
	@InjectMocks
	private MedicalRecordsController medicalRecordController;
	
	@Mock
	private IMedicalRecordService medicalRecordService;
	
	private static List<MedicalRecord> listMock = new ArrayList<>();
	private static MedicalRecord medicalRecordTest = new MedicalRecord();
	
	@BeforeAll
	public static void setUp() {
		medicalRecordTest.setFirstName("firstName");
		medicalRecordTest.setLastName("lastName");		
		medicalRecordTest.setBirthdate(LocalDate.now().minusYears(30));
		Collections.addAll(listMock, medicalRecordTest);
	}
	
	@Test
	void getMedicalRecords_Test() throws MedicalRecordNotFoundException {
		
		// GIVEN
		when(medicalRecordService.getAllMedicalRecords()).thenReturn(listMock);
		
		// WHEN 
		ResponseEntity<List<MedicalRecord>> result = medicalRecordController.getAllMedicalRecords();
		
		// THEN 
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		assertEquals(listMock, result.getBody());
	}

	@Test
	void createMedicalRecord_Test() throws MedicalRecordAlreadyExistsException {
		
		// GIVEN
		when(medicalRecordService.saveMedicalRecord(any(MedicalRecord.class))).thenReturn(medicalRecordTest);
		
		// WHEN 
		ResponseEntity<MedicalRecord> result = medicalRecordController.createMedicalRecord(new MedicalRecord());
		
		// THEN 
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}
	
	@Test
	void updateMedicalRecord_Test() throws MedicalRecordNotFoundException {
		
		// GIVEN
		when(medicalRecordService.updateMedicalRecord(any(MedicalRecord.class))).thenReturn(medicalRecordTest);
		
		// WHEN 
		ResponseEntity<MedicalRecord> result = medicalRecordController.updateMedicalRecord(medicalRecordTest);
		
		// THEN 
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
	}
	
	@Test
	void deleteMedicalRecord_Test() throws MedicalRecordNotFoundException {
		
		// GIVEN
		
		// WHEN 
		ResponseEntity<Void> result = medicalRecordController.deleteMedicalRecord(medicalRecordTest.getFirstName(), medicalRecordTest.getLastName());
		
		// THEN 
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
	}

}

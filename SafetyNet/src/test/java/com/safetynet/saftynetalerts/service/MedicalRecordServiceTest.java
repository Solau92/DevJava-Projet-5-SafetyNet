package com.safetynet.saftynetalerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.saftynetalerts.exception.MedicalRecordAlreadyExistsException;
import com.safetynet.saftynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.repository.MedicalRecordsRepository;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceTest {
	
	@InjectMocks
	private MedicalRecordService medicalRecordService;
	
	@Mock
	private MedicalRecordsRepository medicalRecords;
	
	private static List<MedicalRecord> listMock = new ArrayList<>();
	private static MedicalRecord medicalRecordTest = new MedicalRecord();
	
	@BeforeAll
	public static void setUp() {
		medicalRecordTest.setFirstName("firstName");
		medicalRecordTest.setLastName("lastName");
		Collections.addAll(listMock, medicalRecordTest);
	}
	
	@Test
	void getAllMedicalRecords_Succes_Test() throws MedicalRecordNotFoundException {
		
		//GIVEN 
		when(medicalRecords.getAllMedicalRecords()).thenReturn(listMock);
		
		//WHEN 
		List<MedicalRecord> list = medicalRecordService.getAllMedicalRecords();
		
		//THEN 
		verify(medicalRecords, Mockito.times(1)).getAllMedicalRecords();
		assertEquals(medicalRecordTest, list.get(0));
	}
	
	@Test
	void getAllMedicalRecords_NoResult_Test() {
		
		//GIVEN 
		when(medicalRecords.getAllMedicalRecords()).thenReturn(new ArrayList<>());
		
		//WHEN 
		MedicalRecordNotFoundException result = assertThrows(MedicalRecordNotFoundException.class, () -> medicalRecordService.getAllMedicalRecords());
		
		//THEN 
		assertEquals("No medical record found", result.getMessage());
	}
	
	@Test
	void saveMedicalRecord_Success_Test() throws MedicalRecordAlreadyExistsException {
		
		// GIVEN
		when(medicalRecords.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(new ArrayList<>());
		when(medicalRecords.save(any(MedicalRecord.class))).thenReturn(medicalRecordTest);

		// WHEN 
		MedicalRecord medicalRecord = medicalRecordService.saveMedicalRecord(medicalRecordTest);
		
		// THEN 
		assertEquals(medicalRecordTest, medicalRecord);
	}
	
	@Test
	void saveMedicalRecord_Fail_Test() {
		
		// GIVEN
		when(medicalRecords.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(listMock);

		// WHEN 
		MedicalRecordAlreadyExistsException result = assertThrows(MedicalRecordAlreadyExistsException.class, () -> medicalRecordService.saveMedicalRecord(medicalRecordTest));
		
		// THEN 
		assertEquals("firstName lastName's medical record already exists", result.getMessage());
	}
	
	@Test
	void updateMedicalRecord_Success_Test() throws MedicalRecordNotFoundException {
		
		// GIVEN
		when(medicalRecords.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(listMock);
		when(medicalRecords.update(any(MedicalRecord.class))).thenReturn(medicalRecordTest);

		// WHEN 
		MedicalRecord medicalRecord = medicalRecordService.updateMedicalRecord(medicalRecordTest);
		
		// THEN 
		assertEquals(medicalRecordTest, medicalRecord);		
	}
	
	@Test
	void updateMedicalRecord_Fail_Test() throws MedicalRecordNotFoundException {
		
		// GIVEN
		when(medicalRecords.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(new ArrayList<>());

		// WHEN 
		MedicalRecordNotFoundException result = assertThrows(MedicalRecordNotFoundException.class, () -> medicalRecordService.updateMedicalRecord(medicalRecordTest));
		
		// THEN 
		assertEquals("No medical record found", result.getMessage());	
	}
	
	@Test
	void deleteMedicalRecord_Success_Test() throws MedicalRecordNotFoundException {
		
		// GIVEN
		when(medicalRecords.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(listMock);

		// WHEN 
		medicalRecordService.deleteMedicalRecord("firstName", "lastName");
		
		// THEN 
		verify(medicalRecords, Mockito.times(1)).deleteMedicalRecord("firstName", "lastName");		
	}
	
	@Test
	void deleteMedicalRecord_Fail_Test() throws MedicalRecordNotFoundException {
		
		// GIVEN
		when(medicalRecords.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(new ArrayList<>());

		// WHEN 
		MedicalRecordNotFoundException result = assertThrows(MedicalRecordNotFoundException.class, () -> medicalRecordService.deleteMedicalRecord("firstName", "lastName"));
		
		// THEN 
		assertEquals("No medical record found for firstName lastName", result.getMessage());	
	}
	
	@Test
	void isPersonAdult_Yes_Test() throws MedicalRecordNotFoundException {
		
		// GIVEN
		when(medicalRecords.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(listMock);
		when(medicalRecords.isPersonAdult(anyString(), anyString())).thenReturn(true);

		// WHEN 
		Boolean result = medicalRecordService.isPersonAdult("firstName", "lastName");
		
		// THEN 
		assertTrue(result);
	}
	
	@Test
	void isPersonAdult_No_Test() throws MedicalRecordNotFoundException {
		
		// GIVEN
		when(medicalRecords.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(listMock);
		when(medicalRecords.isPersonAdult(anyString(), anyString())).thenReturn(false);

		// WHEN 
		Boolean result = medicalRecordService.isPersonAdult("firstName", "lastName");
		
		// THEN 
		assertFalse(result);
	}
	
	@Test
	void isPersonAdult_PersonNotFound_Test() throws MedicalRecordNotFoundException {
		
		// GIVEN
		when(medicalRecords.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(new ArrayList<>());

		// WHEN 
		MedicalRecordNotFoundException result = assertThrows(MedicalRecordNotFoundException.class, () -> medicalRecordService.isPersonAdult("firstName", "lastName"));
		
		// THEN 
		assertEquals("No medical record found for firstName lastName", result.getMessage());	
	}
	
	@Test
	void getMedicalRecordsByFirstNameAndLastName_Success_Test() throws MedicalRecordNotFoundException {
		
		// GIVEN
		when(medicalRecords.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(listMock);
		
		// WHEN 
		List<MedicalRecord> list = medicalRecordService.getMedicalRecordsByFirstNameAndLastName("firstName", "lastName");
		
		// THEN 
		verify(medicalRecords, Mockito.times(1)).getMedicalRecordsByFirstNameAndLastName(anyString(), anyString());
		assertEquals(medicalRecordTest, list.get(0));
	}
	
	@Test
	void getMedicalRecordsByFirstNameAndLastName_NoResult_Test() {
		
		// GIVEN
		when(medicalRecords.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(new ArrayList<>());
		
		// WHEN 
		MedicalRecordNotFoundException result = assertThrows(MedicalRecordNotFoundException.class, () -> medicalRecordService.getMedicalRecordsByFirstNameAndLastName("firstName", "lastName"));
		
		// THEN 
		assertEquals("firstName lastName's medical record was not found", result.getMessage());
	}
	
}

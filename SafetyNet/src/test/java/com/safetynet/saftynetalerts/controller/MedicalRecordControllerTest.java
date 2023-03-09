package com.safetynet.saftynetalerts.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

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

}

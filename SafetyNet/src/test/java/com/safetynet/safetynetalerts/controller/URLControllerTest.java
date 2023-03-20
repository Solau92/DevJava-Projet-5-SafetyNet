package com.safetynet.safetynetalerts.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOChildAlert;
import com.safetynet.safetynetalerts.model.DTOFire;
import com.safetynet.safetynetalerts.model.DTOFirestation;
import com.safetynet.safetynetalerts.model.DTOFlood;
import com.safetynet.safetynetalerts.model.DTOPersonInfo;
import com.safetynet.safetynetalerts.service.IURLChildAlertService;
import com.safetynet.safetynetalerts.service.IURLFireService;
import com.safetynet.safetynetalerts.service.IURLFirestationService;
import com.safetynet.safetynetalerts.service.IURLFloodService;
import com.safetynet.safetynetalerts.service.IURLPersonInfoService;
import com.safetynet.safetynetalerts.service.IURLPhoneAlertService;

@SpringBootTest
class URLControllerTest {

	@InjectMocks
	private URLController uRLController;
	
	@Mock 
	private IURLFirestationService uRLFirestationService;
	
	@Mock
	private IURLChildAlertService uRLChildAlertService;
	
	@Mock
	private IURLPhoneAlertService uRLPhoneAlertService;
	
	@Mock
	private IURLFireService uRLFireService;
	
	@Mock
	private IURLFloodService uRLFloodService;
	
	@Mock
	private IURLPersonInfoService uRLPersonInfoService;
	
	@Test
	void getFirestation_Test() throws PersonNotFoundException, MedicalRecordNotFoundException, FirestationNotFoundException {
		
		// GIVEN 
		DTOFirestation dTOFirestation = new DTOFirestation();
		dTOFirestation.setNumberOfAdults(5);
		when(uRLFirestationService.getFirestation(anyInt())).thenReturn(dTOFirestation);
		
		// WHEN 
		ResponseEntity<DTOFirestation> result = uRLController.getFirestation(1);
		
		// THEN 
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		assertEquals(5, result.getBody().getNumberOfAdults());		
	}
	
	@Test	
	void getChildAlert_Test() throws PersonNotFoundException, MedicalRecordNotFoundException {
		
		// GIVEN 
		List<DTOChildAlert> listDTOChildAlert = new ArrayList<>();
		DTOChildAlert dTOChildAlert = new DTOChildAlert();
		dTOChildAlert.setLastName("lastName");
		dTOChildAlert.setAge(10);
		listDTOChildAlert.add(dTOChildAlert);
		when(uRLChildAlertService.getChildAlert(anyString())).thenReturn(listDTOChildAlert);
		
		// WHEN 
		ResponseEntity<List<DTOChildAlert>> result = uRLController.getChildAlert("address");
		
		// THEN 
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		assertEquals(10, result.getBody().get(0).getAge());
	}
	
	@Test
	void getPhoneAlert_Test() throws PersonNotFoundException, FirestationNotFoundException {
		
		// GIVEN 
		Set<String> listPhoneNumbers = new HashSet<>();
		listPhoneNumbers.add("111");
		listPhoneNumbers.add("222");
		when(uRLPhoneAlertService.getPhoneAlert(anyInt())).thenReturn(listPhoneNumbers);
		
		// WHEN 
		ResponseEntity<Set<String>> result = uRLController.getPhoneAlert(1);
		
		// THEN 
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		assertTrue(listPhoneNumbers.contains("222"));	
	}
	
	@Test
	void getFire_Test() throws PersonNotFoundException, MedicalRecordNotFoundException, FirestationNotFoundException {
		
		// GIVEN 
		DTOFire dTOFire = new DTOFire();
		dTOFire.setStationId(1);
		when(uRLFireService.getFire(anyString())).thenReturn(dTOFire);
		
		// WHEN 
		ResponseEntity<DTOFire> result = uRLController.getFire("address");
		
		// THEN 
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		assertEquals(1, result.getBody().getStationId());
	}
	
	@Test
	void getFlood_Test() throws PersonNotFoundException, MedicalRecordNotFoundException, FirestationNotFoundException {
		
		// GIVEN 
		List<DTOFlood> listDTOFlood = new ArrayList<>();
		DTOFlood dTOFlood = new DTOFlood();
		dTOFlood.setAddress("address");
		listDTOFlood.add(dTOFlood);
		when(uRLFloodService.getFlood(anyList())).thenReturn(listDTOFlood);
		
		// WHEN 
		List<Integer> listStations = new ArrayList<>();
		listStations.add(1);
		listStations.add(2);
		ResponseEntity<List<DTOFlood>> result = uRLController.getFlood(listStations);
		
		// THEN 
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		assertEquals("address", result.getBody().get(0).getAddress());
	}
	
	@Test
	void getPersonInfo_Test() throws PersonNotFoundException, MedicalRecordNotFoundException {
		
		// GIVEN 
		List<DTOPersonInfo> listDTOPerson = new ArrayList<>();
		DTOPersonInfo dTOPersonInfo = new DTOPersonInfo();
		dTOPersonInfo.setLastName("Boyd");
		dTOPersonInfo.setEmail("jaboyd@email.com");
		listDTOPerson.add(dTOPersonInfo);
		when(uRLPersonInfoService.getPersonInfo(anyString(), anyString())).thenReturn(listDTOPerson);
		
		// WHEN 
		ResponseEntity<List<DTOPersonInfo>> result = uRLController.getPersonInfo("John", "Boyd");
		
		// THEN 
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		assertEquals("jaboyd@email.com", result.getBody().get(0).getEmail());
	}
	
}

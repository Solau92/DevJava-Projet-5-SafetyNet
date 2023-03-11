package com.safetynet.saftynetalerts.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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

import com.safetynet.saftynetalerts.exception.FirestationAlreadyExistsException;
import com.safetynet.saftynetalerts.exception.FirestationNotFoundException;
import com.safetynet.saftynetalerts.model.Firestation;
import com.safetynet.saftynetalerts.service.IFirestationService;

@SpringBootTest
public class FirestationControllerTest {

	  @InjectMocks
	  private FirestationsController firestationController;
	  
	  @Mock
	  private IFirestationService firestationService;
	  
		private static List<Firestation> listMock = new ArrayList<>();
		private static Firestation firestationTest = new Firestation();
		
		@BeforeAll
		public static void setUp() {
			firestationTest.setAddress("address");
			firestationTest.setIdStation(1);		
			Collections.addAll(listMock,firestationTest);
		}
		
		@Test
		void getAllFirestations_Test() throws FirestationNotFoundException {
			
			// GIVEN
			when(firestationService.getAllFirestations()).thenReturn(listMock);
			
			// WHEN 
			ResponseEntity<List<Firestation>> result = firestationController.getFirestations();
			
			// THEN 
			assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
			assertEquals(listMock, result.getBody());
		}

		@Test
		void createFirestation_Test() throws FirestationAlreadyExistsException {
			
			// GIVEN
			when(firestationService.saveFirestation(any(Firestation.class))).thenReturn(firestationTest);
			
			// WHEN 
			ResponseEntity<Firestation> result = firestationController.createFirestation(new Firestation());
			
			// THEN 
			assertEquals(HttpStatus.CREATED, result.getStatusCode());
		}
		
		@Test
		void updateFirestation_Test() throws FirestationNotFoundException {
			
			// GIVEN
			when(firestationService.updateFirestation(any(Firestation.class))).thenReturn(firestationTest);
			
			// WHEN 
			ResponseEntity<Firestation> result = firestationController.updateFirestation(firestationTest);
			
			// THEN 
			assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		}
		
		@Test
		void deleteFirestation_Test() throws FirestationNotFoundException {
			
			// GIVEN
			
			// WHEN 
			ResponseEntity<Void> result = firestationController.deleteFirestationByAddress(firestationTest.getAddress());
			
			// THEN 
			assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		}
		
		@Test
		void getFirestationsAddressesById() throws FirestationNotFoundException {
			
			List<String> addresses = new ArrayList<>();
			addresses.add("address1");
			
			// GIVEN
			when(firestationService.getAddressesWithId(anyInt())).thenReturn(addresses);
			
			// WHEN 
			ResponseEntity<List<String>> result = firestationController.getFirestationsAddressesById(1);
			
			// THEN 
			assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
			assertEquals(addresses, result.getBody());
		}
		
		@Test
		void getFirestationsIdByAddress() throws FirestationNotFoundException {
			
			int id = 1;
			
			// GIVEN
			when(firestationService.getIdWithAddress(anyString())).thenReturn(id);
			
			// WHEN 
			ResponseEntity<Integer> result = firestationController.getFirestationsIdByAddress("address");
			
			// THEN 
			assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
			assertEquals(1, result.getBody());
		}
	  
}

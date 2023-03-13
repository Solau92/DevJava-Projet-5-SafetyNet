package com.safetynet.safetynetalerts.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.FirestationsRepository;

@ExtendWith(MockitoExtension.class)
class FirestationsRepositoryTest {
	
	@InjectMocks
	private FirestationsRepository firestationRepository;
	
	private static List<Firestation> firestations = new ArrayList<>();
	
	@BeforeEach
	public void setUp() {
		Firestation firestation1 = new Firestation();
		firestation1.setAddress("address1");
		firestation1.setIdStation(1);
		Firestation firestation2 = new Firestation();
		firestation2.setAddress("address2");
		firestation2.setIdStation(2);
		Collections.addAll(firestations, firestation1, firestation2);
		firestationRepository.setFirestations(firestations);
	}
	
	@AfterEach
	public void tearDown() {
		firestations = new ArrayList<>();
	}
	
	@Test
	void getAllFirestations_Success_Test() {
		
		// GIVEN 

		// WHEN 
		List<Firestation> list = firestationRepository.getAllFirestations();
		
		// THEN 
		assertEquals(firestations, list);
	}
	
	@Test
	void save_Success_Test() {
		
		// GIVEN 
		Firestation firestation3 = new Firestation();
		firestation3.setAddress("address3");
		firestation3.setIdStation(3);

		// WHEN 
		Firestation firestationSaved = firestationRepository.save(firestation3);
		
		// THEN 
		assertEquals(firestationSaved, firestations.get(2));
	}
	
	@Test
	void update_Succcess_Test() {
		
		// GIVEN 
		Firestation firestation3 = new Firestation();
		firestation3.setAddress("address2");
		firestation3.setIdStation(3);

		// WHEN 
		Firestation firestationUpdated = firestationRepository.update(firestation3);
		
		// THEN 
		assertEquals(firestationUpdated, firestations.get(1));
		assertEquals(3, firestationUpdated.getIdStation());
	}
	
	@Test
	void deleteByAddress_Success_Test() {
		
		// WHEN 
		firestationRepository.deleteFirestation("address1");
		
		// THEN 
		assertEquals(1, firestations.size());	
	}
	
	@Test
	void deleteByAddress_Fail_Test() {
		
		// WHEN 
		firestationRepository.deleteFirestation("address");
		
		// THEN 
		assertEquals(2, firestations.size());	
	}
	
	@Test
	void deleteById_Success_Test() {
		
		// WHEN 
		firestationRepository.deleteFirestation(2);
		
		// THEN 
		assertEquals(1, firestations.size());	
	}
	
	@Test
	void deleteById_Fail_Test() {
		
		// WHEN 
		firestationRepository.deleteFirestation(3);
		
		// THEN 
		assertEquals(2, firestations.size());	
	}
	
		@Test
	void getAddressesWithId_Success_Test() {
		
		// GIVEN 
		
		// WHEN 
		List<String> list = firestationRepository.getAddressesWithId(2);

		// THEN 
		assertEquals("address2", list.get(0));	
	}
	
	@Test
	void getIdWithAddress_Success_Test() {
		
		// GIVEN 
		
		// WHEN 
		int id = firestationRepository.getIdWithAddress("address2");

		// THEN 
		assertEquals(2, id);	
	}
	
	@Test
	void getIdWithAddress_NoResult_Test() {
		
		// GIVEN 
		
		// WHEN 
		int id = firestationRepository.getIdWithAddress("address");

		// THEN 
		assertEquals(0, id);	
	}
	
	@Test
	void getAllAddresses_Success_Test() {
		
		// GIVEN 
		
		// WHEN 
		List<String> list = firestationRepository.getAllAddresses();

		// THEN 
		assertEquals(2, list.size());	
		assertEquals("address2", list.get(1));
	}
		
	
}

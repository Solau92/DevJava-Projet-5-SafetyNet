package com.safetynet.saftynetalerts.repository;

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

import com.safetynet.saftynetalerts.model.FirestationSpot;
import com.safetynet.saftynetalerts.model.Person;

@ExtendWith(MockitoExtension.class)
public class FirestationsRepositoryTest {
	
	@InjectMocks
	private FirestationsRepository firestationRepository;
	
	private static List<FirestationSpot> firestations = new ArrayList<>();
	
	@BeforeEach
	public void setUp() {
		FirestationSpot firestation1 = new FirestationSpot();
		firestation1.setAddress("address1");
		firestation1.setIdStation(1);
		FirestationSpot firestation2 = new FirestationSpot();
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
		List<FirestationSpot> list = firestationRepository.getAllFirestations();
		
		// THEN 
		assertEquals(firestations, list);
	}
	
	@Test
	public void save_Success_Test() {
		
		// GIVEN 
		FirestationSpot firestation3 = new FirestationSpot();
		firestation3.setAddress("address3");
		firestation3.setIdStation(3);

		// WHEN 
		FirestationSpot firestationSaved = firestationRepository.save(firestation3);
		
		// THEN 
		assertEquals(firestationSaved, firestations.get(2));
	}
	
	@Test
	void update_Succcess_Test() {
		
		// GIVEN 
		FirestationSpot firestation3 = new FirestationSpot();
		firestation3.setAddress("address2");
		firestation3.setIdStation(3);

		// WHEN 
		FirestationSpot firestationUpdated = firestationRepository.update(firestation3);
		
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

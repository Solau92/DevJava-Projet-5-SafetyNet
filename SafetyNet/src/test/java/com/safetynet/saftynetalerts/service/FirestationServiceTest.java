package com.safetynet.saftynetalerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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

import com.safetynet.saftynetalerts.exception.FirestationAlreadyExistsException;
import com.safetynet.saftynetalerts.exception.FirestationNotFoundException;
import com.safetynet.saftynetalerts.model.Firestation;
import com.safetynet.saftynetalerts.repository.FirestationsRepository;

@ExtendWith(MockitoExtension.class)
public class FirestationServiceTest {

	@InjectMocks
	private FirestationService firestationService;

	@Mock
	private FirestationsRepository firestations;

	@Mock
	private List<String> addresses;

	private static List<Firestation> listMock = new ArrayList<>();
	private static Firestation firestationTest = new Firestation();
	private static List<String> listAddressesMock = new ArrayList<>();

	@BeforeAll
	public static void setUp() {
		firestationTest.setAddress("address");
		firestationTest.setIdStation(1);
		Collections.addAll(listMock, firestationTest);
		listAddressesMock.add("address");
	}

	@Test
	void getAllFirestations_Success_Test() throws FirestationNotFoundException {
		
		// GIVEN 
		when(firestations.getAllFirestations()).thenReturn(listMock);
		
		// WHEN 
		List<Firestation> list = firestationService.getAllFirestations();
		
		// THEN 
		verify(firestations, Mockito.times(1)).getAllFirestations();
		assertEquals(firestationTest, list.get(0));		
	}

	@Test
	void getAllFirestations_NoResult_Test() throws FirestationNotFoundException {
		
		// GIVEN 
		when(firestations.getAllFirestations()).thenReturn(new ArrayList<>());
		
		// WHEN 
		FirestationNotFoundException result = assertThrows(FirestationNotFoundException.class, () -> firestationService.getAllFirestations());
		
		// THEN 
		verify(firestations, Mockito.times(1)).getAllFirestations();
		assertEquals("No firestation found", result.getMessage());		
	}

	@Test
	void saveFirestation_Success_Test() throws FirestationAlreadyExistsException {
		
		// GIVEN
		when(firestations.getAllAddresses()).thenReturn(new ArrayList<>());
//		when(addresses.contains(anyString())).thenReturn(false);
		when(firestations.save(any(Firestation.class))).thenReturn(firestationTest);

		// WHEN 
		Firestation firestation = firestationService.saveFirestation(firestationTest);
		
		// THEN 
		assertEquals(firestationTest, firestation);
	}

	@Test
	void saveFirestation_Fail_Test() throws FirestationAlreadyExistsException {
		
		// GIVEN
		when(firestations.getAllAddresses()).thenReturn(listAddressesMock);
//		when(addresses.contains(anyString())).thenReturn(true);

		// WHEN 
		FirestationAlreadyExistsException result = assertThrows(FirestationAlreadyExistsException.class, () -> firestationService.saveFirestation(firestationTest));
		
		// THEN 
		assertEquals("This address is already registred", result.getMessage());
	}

	@Test
	void updateFirestation_Success_Test() throws FirestationNotFoundException {
		
		// GIVEN
		when(firestations.getAllAddresses()).thenReturn(listAddressesMock);
		when(firestations.update(any(Firestation.class))).thenReturn(firestationTest);

		// WHEN 
		Firestation firestation = firestationService.updateFirestation(firestationTest);
		
		// THEN 
		assertEquals(firestationTest, firestation);		
	}

	@Test
	void updateFirestation_Fail_Test() throws FirestationNotFoundException {
		
		// GIVEN
		when(firestations.getAllAddresses()).thenReturn(new ArrayList<>());

		// WHEN 
		FirestationNotFoundException result = assertThrows(FirestationNotFoundException.class, () -> firestationService.updateFirestation(firestationTest));
		
		// THEN 
		assertEquals("Firestation spot not found for this address", result.getMessage());	
	}

	@Test
	void deleteFirestationByAddress_Success_Test() throws FirestationNotFoundException {
		
		// GIVEN
		when(firestations.getAllAddresses()).thenReturn(listAddressesMock);

		// WHEN 
		firestationService.deleteFirestationByAddress("address");
		
		// THEN 
		verify(firestations, Mockito.times(1)).deleteFirestation("address");		
	}

	@Test
	void deleteFirestationByAddress_Fail_Test() throws FirestationNotFoundException {
		
		// GIVEN
		when(firestations.getAllAddresses()).thenReturn(new ArrayList<>());

		// WHEN 
		FirestationNotFoundException result = assertThrows(FirestationNotFoundException.class, () -> firestationService.deleteFirestationByAddress("address"));
		
		// THEN 
		assertEquals("Firestation spot not found for this address", result.getMessage());	
	}
		
//	@Test
//	void deleteFirestationById_Success_Test() throws FirestationNotFoundException {
//		
//		// GIVEN
//
//		// WHEN 
//		firestationService.deleteFirestationById(1);
//		
//		// THEN 
//		verify(firestations, Mockito.times(1)).deleteFirestation(1);		
//	}
//
//	@Test
//	void deleteFirestationById_Fail_Test() throws FirestationNotFoundException {
//		
//		// GIVEN
//
//		// WHEN 
//		FirestationNotFoundException result = assertThrows(FirestationNotFoundException.class, () -> firestationService.deleteFirestationById(0));
//		
//		// THEN 
//		assertEquals("No address found for the station : 0", result.getMessage());	
//	}
	

	@Test
	void getAddressesWithId_Success_Test() throws FirestationNotFoundException {
		
		// GIVEN 
		when(firestations.getAddressesWithId(anyInt())).thenReturn(listAddressesMock);
		
		// WHEN 
		List<String> list = firestationService.getAddressesWithId(1);
		
		// THEN 
		verify(firestations, Mockito.times(1)).getAddressesWithId(1);
		assertEquals(listAddressesMock.get(0), list.get(0));		
	}

	@Test
	void getAddressesWithId_NoResult_Test() throws FirestationNotFoundException {
		
		// GIVEN 
		when(firestations.getAddressesWithId(anyInt())).thenReturn(new ArrayList<>());
		
		// WHEN 
		FirestationNotFoundException result = assertThrows(FirestationNotFoundException.class, () -> firestationService.getAddressesWithId(1));
		
		// THEN 
		verify(firestations, Mockito.times(1)).getAddressesWithId(1);
		assertEquals("No address found for the station : 1", result.getMessage());		
	}

	@Test
	void getIdWithAddress_Success_Test() throws FirestationNotFoundException {
		
		// GIVEN 
		when(firestations.getIdWithAddress(anyString())).thenReturn(1);
		
		// WHEN 
		int id = firestationService.getIdWithAddress("address");
		
		// THEN 
		verify(firestations, Mockito.times(1)).getIdWithAddress("address");
		assertEquals(1, firestationTest.getIdStation());		
	}

	@Test
	void getIdWithAddress_NoResult_Test() throws FirestationNotFoundException {
		
		// GIVEN 
		when(firestations.getIdWithAddress(anyString())).thenReturn(0);
		
		// WHEN 
		FirestationNotFoundException result = assertThrows(FirestationNotFoundException.class, () -> firestationService.getIdWithAddress("address"));
		
		// THEN 
		verify(firestations, Mockito.times(1)).getIdWithAddress("address");
		assertEquals("Station id not found, address does not exists", result.getMessage());		
	}

	@Test
	void getAllAddresses_Success_Test() {
		
		// GIVEN 
		when(firestations.getAllAddresses()).thenReturn(listAddressesMock);
		
		// WHEN 
		List<String> list = firestationService.getAllAddresses();
		
		// THEN 
		verify(firestations, Mockito.times(1)).getAllAddresses();
		assertEquals(firestationTest.getAddress(), list.get(0));		
	}

}

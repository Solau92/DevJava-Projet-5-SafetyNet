package com.safetynet.safetynetalerts.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOFlood;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

@ExtendWith(MockitoExtension.class)
class URLFloodServiceTest {

	@InjectMocks
	private URLFloodService uRLFloodService;

	@Mock
	private IPersonService personService;

	@Mock
	private IMedicalRecordService medicalRecordService;

	@Mock
	private IFirestationService firestationService;

	@Test
	void getFlood_Success_Test()
			throws PersonNotFoundException, MedicalRecordNotFoundException, FirestationNotFoundException {

		// GIVEN

		List<Integer> stationIdList = new ArrayList<>();
		stationIdList.add(1);

		List<String> addressesList = new ArrayList<>();
		addressesList.add("address1");
		addressesList.add("address2");
		when(firestationService.getAddressesWithId(anyInt())).thenReturn(addressesList);

		List<Person> personsList1 = new ArrayList<>();
		Person personTest1 = new Person();
		personTest1.setFirstName("firstName1");
		personTest1.setLastName("lastName1");
		personTest1.setPhone("1111");
		personsList1.add(personTest1);

		List<Person> personsList2 = new ArrayList<>();
		Person personTest2 = new Person();
		personTest2.setFirstName("firstName2");
		personTest2.setLastName("lastName2");
		personTest2.setPhone("2222");
		personsList2.add(personTest2);
		Person personTest3 = new Person();
		personTest3.setFirstName("firstName3");
		personTest3.setLastName("lastName2");
		personTest3.setPhone("3333");
		personsList2.add(personTest3);
		when(personService.getPersonsByAddress(anyString())).thenReturn(personsList1).thenReturn(personsList2);

		List<MedicalRecord> medicalRecordList1 = new ArrayList<>();
		MedicalRecord medicalRecordTest1 = new MedicalRecord();
		medicalRecordTest1.setFirstName("firstName1");
		medicalRecordTest1.setLastName("lastName1");
		medicalRecordTest1.setBirthdate(LocalDate.now().minusYears(30));
		medicalRecordList1.add(medicalRecordTest1);
		List<MedicalRecord> medicalRecordList2 = new ArrayList<>();
		MedicalRecord medicalRecordTest2 = new MedicalRecord();
		medicalRecordTest2.setFirstName("firstName2");
		medicalRecordTest2.setLastName("lastName2");
		medicalRecordTest2.setBirthdate(LocalDate.now().minusYears(20));
		medicalRecordList2.add(medicalRecordTest2);
		MedicalRecord medicalRecordTest3 = new MedicalRecord();
		medicalRecordTest3.setFirstName("firstName3");
		medicalRecordTest3.setLastName("lastName2");
		medicalRecordTest3.setBirthdate(LocalDate.now().minusYears(25));
		medicalRecordList2.add(medicalRecordTest3);
		when(medicalRecordService.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString()))
				.thenReturn(medicalRecordList1).thenReturn(medicalRecordList2);

		// WHEN
		List<DTOFlood> dtoFloodList = uRLFloodService.getFlood(stationIdList);

		// THEN
		assertEquals("address1", dtoFloodList.get(0).getAddress());
		assertEquals("address2", dtoFloodList.get(1).getAddress());

		assertTrue(dtoFloodList.get(0).getFamilyList().containsKey("lastName1"));
		assertTrue(dtoFloodList.get(1).getFamilyList().containsKey("lastName2"));

		assertEquals(30, dtoFloodList.get(0).getFamilyList().get("lastName1").get(0).getAge());
		assertEquals("2222", dtoFloodList.get(1).getFamilyList().get("lastName2").get(0).getPhone());
		assertEquals("3333", dtoFloodList.get(1).getFamilyList().get("lastName2").get(1).getPhone());

	}

}

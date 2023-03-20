package com.safetynet.safetynetalerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Fail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOChildAlert;
import com.safetynet.safetynetalerts.model.DTOPersonInfo;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

import junit.framework.AssertionFailedError;

@ExtendWith(MockitoExtension.class)
class URLChildAlertServiceTest {

	@InjectMocks
	private URLChildAlertService uRLChildAlertService;

	@Mock
	private IPersonService personService;

	@Mock
	private IMedicalRecordService medicalRecordService;

	@Test
	void getChildAlert_Success_Test() throws PersonNotFoundException, MedicalRecordNotFoundException {

		// GIVEN
		List<Person> personList = new ArrayList<>();
		Person personTest1 = new Person();
		personTest1.setFirstName("firstNameAdult");
		personTest1.setLastName("lastName");
		personTest1.setAddress("address");
		Person personTest2 = new Person();
		personTest2.setFirstName("firstNameChild");
		personTest2.setLastName("lastName");
		personTest2.setAddress("address");
		personList.add(personTest1);
		personList.add(personTest2);

		List<MedicalRecord> medicalRecordListAdult = new ArrayList<>();
		MedicalRecord medicalRecordTestAdult = new MedicalRecord();
		medicalRecordTestAdult.setFirstName("firstNameAdult");
		medicalRecordTestAdult.setLastName("lastNameAdult");
		medicalRecordTestAdult.setBirthdate(LocalDate.now().minusYears(30));
		medicalRecordListAdult.add(medicalRecordTestAdult);

		List<MedicalRecord> medicalRecordListChild = new ArrayList<>();
		MedicalRecord medicalRecordTestChild = new MedicalRecord();
		medicalRecordTestChild.setFirstName("firstNameChild");
		medicalRecordTestChild.setLastName("lastNameChild");
		medicalRecordTestChild.setBirthdate(LocalDate.now().minusYears(10));
		medicalRecordListChild.add(medicalRecordTestChild);

		when(personService.getPersonsByAddress(anyString())).thenReturn(personList);

		when(medicalRecordService.getMedicalRecordsByFirstNameAndLastName(anyString(), anyString()))
				.thenReturn(medicalRecordListAdult).thenReturn(medicalRecordListChild);
		when(medicalRecordService.isPersonAdult(anyString(), anyString())).thenReturn(true).thenReturn(false);

		when(personService.getPersonsByLastNameAndAddress(anyString(), anyString())).thenReturn(personList);

		// WHEN
		List<DTOChildAlert> childAlertList = uRLChildAlertService.getChildAlert("address");

		// THEN
		assertEquals("firstNameChild", childAlertList.get(0).getFirstName());
		assertEquals("firstNameAdult", childAlertList.get(0).getFamilyMembers().get(0).getFirstName());
	}

}

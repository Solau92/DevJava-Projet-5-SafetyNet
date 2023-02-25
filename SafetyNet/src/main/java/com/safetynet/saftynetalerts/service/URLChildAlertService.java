package com.safetynet.saftynetalerts.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.model.DTOChildAlert;
import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.model.Person;

@Service
public class URLChildAlertService implements IURLChildAlertService {

	@Autowired
	private PersonService personService;

	@Autowired
	private MedicalRecordService medicalRecordService;

	@Override
	public List<DTOChildAlert> getChildAlert(String address) {

		List<Person> personList = new ArrayList<Person>();
		List<DTOChildAlert> childAlertList = new ArrayList<DTOChildAlert>();
		List<MedicalRecord> medicalRecordList = new ArrayList<MedicalRecord>();

		// Recherche des personnes à partir d'une adresse
		personList = personService.getPersonsByAddress(address);

		for (Person p : personList) {
			MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordByFirstNameAndLastName(p.getFirstName(),
					p.getLastName());
			if (!medicalRecordService.isPersonAdult(p.getFirstName(), p.getLastName())) {
				// Construction DTO
				DTOChildAlert DTOChild = new DTOChildAlert();
				DTOChild.setFirstName(p.getFirstName());
				DTOChild.setLastName(p.getLastName());
				DTOChild.setAge(ChronoUnit.YEARS.between(medicalRecord.getBirthdate(), LocalDate.now()));

				// Membres de la famille
				List<Person> familyMembers = personService.getPersonByLastNameAndAddress(p.getLastName(),
						p.getAddress());
				DTOChild.setFamilyMembers(familyMembers);
				childAlertList.add(DTOChild);
			}

		}

		return childAlertList;
	}

}

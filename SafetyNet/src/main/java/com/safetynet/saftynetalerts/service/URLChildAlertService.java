package com.safetynet.saftynetalerts.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.saftynetalerts.exception.MoreThanOneMedicalRecordFoundException;
import com.safetynet.saftynetalerts.exception.PersonNotFoundException;
import com.safetynet.saftynetalerts.model.DTOChildAlert;
import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.model.Person;

@Service
public class URLChildAlertService implements IURLChildAlertService {

	private final PersonService personService;

	private final MedicalRecordService medicalRecordService;

	public URLChildAlertService(PersonService personService, MedicalRecordService medicalRecordService) {
		this.personService = personService;
		this.medicalRecordService = medicalRecordService;
	}

	@Override
	public List<DTOChildAlert> getChildAlert(String address)
			throws PersonNotFoundException, MedicalRecordNotFoundException, MoreThanOneMedicalRecordFoundException {

		List<DTOChildAlert> childAlertList = new ArrayList<DTOChildAlert>();

		// Recherche des personnes à partir d'une adresse
		List<Person> personList = personService.getPersonsByAddress(address);

		// je parcours la liste des personnes
		for (Person p : personList) {

			List<MedicalRecord> medicalRecords = medicalRecordService
					.getMedicalRecordsByFirstNameAndLastName(p.getFirstName(), p.getLastName());

			// si mineur
			if (!medicalRecordService.isPersonAdult(p.getFirstName(), p.getLastName())) {
				// Construction DTO
				DTOChildAlert dTOChild = new DTOChildAlert();
				dTOChild.setFirstName(p.getFirstName());
				dTOChild.setLastName(p.getLastName());
				dTOChild.setAge(ChronoUnit.YEARS.between(medicalRecords.get(0).getBirthdate(), LocalDate.now()));

				// Membres de la famille
				List<Person> familyMembers = personService.getPersonByLastNameAndAddress(p.getLastName(),
						p.getAddress());

				// Enlever l'enfant de la liste
				familyMembers.remove(p);

				dTOChild.setFamilyMembers(familyMembers);
				childAlertList.add(dTOChild);
			}

		}

		return childAlertList;
	}

}

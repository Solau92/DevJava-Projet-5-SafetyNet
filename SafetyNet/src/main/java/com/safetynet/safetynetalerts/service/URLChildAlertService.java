package com.safetynet.safetynetalerts.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOChildAlert;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.PersonsRepository;

@Service
public class URLChildAlertService implements IURLChildAlertService {
	
	private final IPersonService personService;

	private final IMedicalRecordService medicalRecordService;

	public URLChildAlertService(IPersonService personService, IMedicalRecordService medicalRecordService) {
		this.personService = personService;
		this.medicalRecordService = medicalRecordService;
	}

	@Override
	public List<DTOChildAlert> getChildAlert(String address)
			throws PersonNotFoundException, MedicalRecordNotFoundException {

		List<DTOChildAlert> childAlertList = new ArrayList<>();

		// Recherche des personnes à partir d'une adresse
		List<Person> personList = personService.getPersonsByAddress(address);

		// je parcours la liste des personnes
		for (Person p : personList) {
			
			System.out.println("Person dans méthode : " + p.toString());

			List<MedicalRecord> medicalRecords = medicalRecordService
					.getMedicalRecordsByFirstNameAndLastName(p.getFirstName(), p.getLastName());

			System.out.println("MedicalRec dans méthode : " + medicalRecords.toString());
			
			System.out.println(!medicalRecordService.isPersonAdult(p.getFirstName(), p.getLastName()));
			
			// si mineur
			if (!medicalRecordService.isPersonAdult(p.getFirstName(), p.getLastName())) {
				
				System.out.println("Person dans méthode dans la boucle : " + p.toString() + " dans la boucle");
				
				// Construction DTO
				DTOChildAlert dTOChild = new DTOChildAlert();
				dTOChild.setFirstName(p.getFirstName());
				dTOChild.setLastName(p.getLastName());
				dTOChild.setAge(ChronoUnit.YEARS.between(medicalRecords.get(0).getBirthdate(), LocalDate.now()));

				// Membres de la famille

				List<Person> familyMembers = personService.getPersonsByLastNameAndAddress(p.getLastName(),
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

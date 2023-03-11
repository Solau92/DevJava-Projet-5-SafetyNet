package com.safetynet.saftynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.exception.FirestationNotFoundException;
import com.safetynet.saftynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.saftynetalerts.exception.PersonNotFoundException;
import com.safetynet.saftynetalerts.model.DTOFirestation;
import com.safetynet.saftynetalerts.model.DTOFirestationPerson;
import com.safetynet.saftynetalerts.model.Person;

@Service
public class URLFirestationService implements IURLFirestationService {

	private final IPersonService personService;

	private final IFirestationService firestationService;

	private final IMedicalRecordService medicalRecordService;

	public URLFirestationService(IPersonService personService, IMedicalRecordService medicalRecordService, IFirestationService firestationService) {
		this.personService = personService;
		this.medicalRecordService = medicalRecordService;
		this.firestationService = firestationService;
	}
	
	@Override
	public DTOFirestation getFirestation(int stationId) throws PersonNotFoundException, MedicalRecordNotFoundException, FirestationNotFoundException {

		DTOFirestation dTOFirestation = new DTOFirestation();
		List<Person> personsList = new ArrayList<>();

		// numéro station --> liste immeubles
		List<String> addressesList = firestationService.getAddressesWithId(stationId);

		// Liste immeubles --> liste personnes
		for (String s : addressesList) {
			personsList.addAll(personService.getPersonsByAddress(s));
		}

		// récupérer les infos des personnes
		dTOFirestation.setFirestationPersons(new ArrayList<>());

		for (Person p : personsList) {
			DTOFirestationPerson dTOPerson = new DTOFirestationPerson();
			dTOPerson.setFirstName(p.getFirstName());
			dTOPerson.setLastName(p.getLastName());
			dTOPerson.setAddress(p.getAddress());
			dTOPerson.setPhone(p.getPhone());
			dTOFirestation.getFirestationPersons().add(dTOPerson);

			// Si personne majeure / mineure : incrémenter liste
			// Pour ça, faut calculer âge, et donc faire appel à MedicalRecord
			if (medicalRecordService.isPersonAdult(p.getFirstName(), p.getLastName())) {
				dTOFirestation.incrementNumberOfAdults();
			} else {
				dTOFirestation.incrementNumberOfChildren();
			}

		}

		return dTOFirestation;
	}

}

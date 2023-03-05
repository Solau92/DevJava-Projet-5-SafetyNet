package com.safetynet.saftynetalerts.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.model.DTOFirePerson;
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
	public DTOFirestation getFirestation(int stationId) {

		DTOFirestation DTOFirestation = new DTOFirestation();
		List<String> addressesList = new ArrayList<String>();
		List<Person> personsList = new ArrayList<Person>();

		// numéro station --> liste immeubles
		addressesList = firestationService.getAddressesWithId(stationId);

		// Liste immeubles --> liste personnes
		for (String s : addressesList) {
			personsList.addAll(personService.getPersonsByAddress(s));
		}

		// récupérer les infos des personnes
		DTOFirestation.setFirestationPersons(new ArrayList<DTOFirestationPerson>());

		for (Person p : personsList) {
			DTOFirestationPerson DTOPerson = new DTOFirestationPerson();
			DTOPerson.setFirstName(p.getFirstName());
			DTOPerson.setLastName(p.getLastName());
			DTOPerson.setAddress(p.getAddress());
			DTOPerson.setPhone(p.getPhone());
			DTOFirestation.getFirestationPersons().add(DTOPerson);

			// Si personne majeure / mineure : incrémenter liste
			// Pour ça, faut calculer âge, et donc faire appel à MedicalRecord
			if (medicalRecordService.isPersonAdult(p.getFirstName(), p.getLastName())) {
				DTOFirestation.incrementNumberOfAdults();
			} else {
				DTOFirestation.incrementNumberOfChildren();
			}

		}

		return DTOFirestation;
	}

}

package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOFirestation;
import com.safetynet.safetynetalerts.model.DTOFirestationPerson;
import com.safetynet.safetynetalerts.model.Person;

@Service
public class URLFirestationService implements IURLFirestationService {

	private final IPersonService personService;

	private final IFirestationService firestationService;

	private final IMedicalRecordService medicalRecordService;

	public URLFirestationService(IPersonService personService, IMedicalRecordService medicalRecordService,
			IFirestationService firestationService) {
		this.personService = personService;
		this.medicalRecordService = medicalRecordService;
		this.firestationService = firestationService;
	}

	/**
	 * Returns an object including a list of persons (with their firstname,
	 * lastname, address and phone number) living at a given address, and the number
	 * of child and the number of adults.
	 * 
	 * @param stationId
	 * @return a DTOFirestation
	 * @throws PersonNotFoundException        if no person was found in the
	 *                                        repository at the given address
	 * @throws MedicalRecordNotFoundException if the medical record of a person was
	 *                                        not found in the repository
	 * @throws FirestationNotFoundException   if no address was found in the
	 *                                        repository for the firestation id
	 *                                        given
	 */
	@Override
	public DTOFirestation getFirestation(int stationId)
			throws PersonNotFoundException, MedicalRecordNotFoundException, FirestationNotFoundException {

		DTOFirestation dTOFirestation = new DTOFirestation();
		List<Person> personsList = new ArrayList<>();

		// List of addresses corresponding to the given firestation id
		List<String> addressesList = firestationService.getAddressesWithId(stationId);

		// List of persons living at all addresses of the previous list
		for (String s : addressesList) {
			personsList.addAll(personService.getPersonsByAddress(s));
		}

		// récupérer les infos des personnes
		dTOFirestation.setFirestationPersons(new ArrayList<>());

		// For each person of the previous list
		for (Person p : personsList) {

			// Create a DTOFirestationPerson and sets different attributes
			DTOFirestationPerson dTOPerson = new DTOFirestationPerson();
			dTOPerson.setFirstName(p.getFirstName());
			dTOPerson.setLastName(p.getLastName());
			dTOPerson.setAddress(p.getAddress());
			dTOPerson.setPhone(p.getPhone());
			// Add the DTOFirestationPerson to the DTOFirestation list of persons
			dTOFirestation.getFirestationPersons().add(dTOPerson);

			// Si personne majeure / mineure : incrémenter liste
			// Pour ça, faut calculer âge, et donc faire appel à MedicalRecord
			// If person is adult
			if (medicalRecordService.isPersonAdult(p.getFirstName(), p.getLastName())) {
				// increments the DTOFirestation number of adults
				dTOFirestation.incrementNumberOfAdults();
			} else {
				// increments the DTOFirestation number of children
				dTOFirestation.incrementNumberOfChildren();
			}

		}

		return dTOFirestation;
	}

}

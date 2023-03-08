package com.safetynet.saftynetalerts.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.exception.FirestationNotFoundException;
import com.safetynet.saftynetalerts.exception.PersonNotFoundException;
import com.safetynet.saftynetalerts.model.Person;

@Service
public class URLPhoneAlertService implements IURLPhoneAlertService {

	private final IPersonService personService;

	private final IFirestationService firestationService;
	
	public URLPhoneAlertService(IPersonService personService, IFirestationService firestationService) {
		this.personService = personService;
		this.firestationService = firestationService;
	}

	@Override
	public Set<String> getPhoneAlert(int stationId) throws PersonNotFoundException, FirestationNotFoundException {
	
		Set<String> phoneList = new HashSet<String>();
		List<Person> personsList = new ArrayList<Person>();

		// Avec la stationId --> liste d'adresses
		List<String>addressesList = firestationService.getAddressesWithId(stationId);

		// liste d'adresse --> liste de personnes
		for (String s : addressesList) {
			personsList.addAll(personService.getPersonsByAddress(s));
		}

		// récupérer les emails
		for (Person p : personsList) {
			// Gère pas les doublons de numéros de tél 
			phoneList.add(p.getPhone());
		}

		return phoneList;
	}

}

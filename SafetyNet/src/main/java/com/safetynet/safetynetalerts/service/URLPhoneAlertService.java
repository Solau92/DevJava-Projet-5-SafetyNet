package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.Person;

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
	
		Set<String> phoneList = new HashSet<>();
		List<Person> personsList = new ArrayList<>();

		// Avec la stationId --> liste d'adresses
		List<String>addressesList = firestationService.getAddressesWithId(stationId);

		// liste d'adresse --> liste de personnes
		for (String s : addressesList) {
			personsList.addAll(personService.getPersonsByAddress(s));
		}

		// récupérer les emails
		for (Person p : personsList) {
			phoneList.add(p.getPhone());
		}

		return phoneList;
	}

}

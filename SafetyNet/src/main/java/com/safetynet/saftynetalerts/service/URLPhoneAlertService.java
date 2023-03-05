package com.safetynet.saftynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<String> getPhoneAlert(int stationId) {
	
		List<String> phoneList = new ArrayList<String>();
		List<String> addressesList = new ArrayList<String>();
		List<Person> personsList = new ArrayList<Person>();

		// Avec la stationId --> liste d'adresses
		addressesList = firestationService.getAddressesWithId(stationId);

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

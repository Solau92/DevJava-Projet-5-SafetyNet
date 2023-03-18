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

	/**
	 * Returns from the repository the list of the phone numbers of the residents
	 * whose addresses are corresponding to the given firestation.
	 * 
	 * @param stationId
	 * @return a Set<String> with the phone numbers
	 * @throws PersonNotFoundException      if no person found in the repository at
	 *                                      a given address
	 * @throws FirestationNotFoundException if no address found in the repository
	 *                                      for the given firestation id
	 */
	@Override
	public Set<String> getPhoneAlert(int stationId) throws PersonNotFoundException, FirestationNotFoundException {

		Set<String> phoneList = new HashSet<>();
		List<Person> personsList = new ArrayList<>();

		// List of addresses corresponding to the stationId
		List<String> addressesList = firestationService.getAddressesWithId(stationId);

		for (String s : addressesList) {
			// Add to the persons list, the list of persons corresponding to the address
			personsList.addAll(personService.getPersonsByAddress(s));
		}

		// Add the phone number of each person to the list
		for (Person p : personsList) {
			phoneList.add(p.getPhone());
		}

		return phoneList;
	}

}

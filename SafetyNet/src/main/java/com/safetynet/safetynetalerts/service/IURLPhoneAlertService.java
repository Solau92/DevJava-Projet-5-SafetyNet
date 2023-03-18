package com.safetynet.safetynetalerts.service;

import java.util.Set;

import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;

public interface IURLPhoneAlertService {

	/**
	 * Returns the list of the phone numbers of the residents whose addresses are
	 * corresponding to the given firestation.
	 * 
	 * @param stationId
	 * @return a Set<String> with the phone numbers
	 * @throws PersonNotFoundException      if no person found at a given address
	 * @throws FirestationNotFoundException if no address found for the given
	 *                                      firestation id
	 */
	public Set<String> getPhoneAlert(int stationId) throws PersonNotFoundException, FirestationNotFoundException;

}

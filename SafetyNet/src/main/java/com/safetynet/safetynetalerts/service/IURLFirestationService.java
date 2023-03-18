package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOFirestation;

public interface IURLFirestationService {

	/**
	 * Returns an object including a list of persons (with their firstname,
	 * lastname, address and phone number) living at a given address, and the number
	 * of child and the number of adults.
	 * 
	 * @param stationId
	 * @return a DTOFirestation
	 * @throws PersonNotFoundException        if no person was found at the given
	 *                                        address
	 * @throws MedicalRecordNotFoundException if the medical record of a person was
	 *                                        not found
	 * @throws FirestationNotFoundException   if no address was found for the
	 *                                        firestation id given
	 */
	DTOFirestation getFirestation(int stationId)
			throws PersonNotFoundException, MedicalRecordNotFoundException, FirestationNotFoundException;

}

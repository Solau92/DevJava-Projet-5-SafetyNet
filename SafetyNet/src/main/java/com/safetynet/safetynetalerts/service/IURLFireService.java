package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOFire;

public interface IURLFireService {

	/**
	 * Returns an object including a list of inhabitants living at the given
	 * address, and the firestation id corresponding.
	 * 
	 * @param address
	 * @return a DTOFire
	 * @throws PersonNotFoundException        if no person was found at the given
	 *                                        address
	 * @throws MedicalRecordNotFoundException if the medical record was not found
	 *                                        for one person
	 * @throws FirestationNotFoundException   if no id was found for the firestation
	 *                                        corresponding to the given address
	 */
	DTOFire getFire(String address)
			throws PersonNotFoundException, MedicalRecordNotFoundException, FirestationNotFoundException;

}

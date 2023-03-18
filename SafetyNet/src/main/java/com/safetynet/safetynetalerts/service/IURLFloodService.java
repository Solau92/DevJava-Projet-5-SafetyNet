package com.safetynet.safetynetalerts.service;

import java.util.List;

import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOFlood;

public interface IURLFloodService {

	/**
	 * Returns a list of families living in the area of a given firestation ; the
	 * families must be grouped by address, and for each inhabitant you must have
	 * the lastname, the phone number, the age, and the medical history (medication,
	 * dosage and allergies).
	 * 
	 * @param stationIdList
	 * @return a list of DTOFlood
	 * @throws PersonNotFoundException        if no person was found at one address
	 * @throws MedicalRecordNotFoundException if the medical record of a person is
	 *                                        not found
	 * @throws FirestationNotFoundException   if no address was found for the given
	 *                                        firestation
	 */
	List<DTOFlood> getFlood(List<Integer> stationIdList)
			throws PersonNotFoundException, MedicalRecordNotFoundException, FirestationNotFoundException;

}

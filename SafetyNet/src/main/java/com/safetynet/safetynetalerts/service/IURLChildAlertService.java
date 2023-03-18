package com.safetynet.safetynetalerts.service;

import java.util.List;

import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOChildAlert;

public interface IURLChildAlertService {

	/**
	 * Returns a list of a list of children (person 18 years old or under) living at
	 * a given address, the list must include child's firstname, lastname, age, and
	 * a list of his household members.
	 * 
	 * @param address
	 * @return a list of DTOChildAlert
	 * @throws PersonNotFoundException        if no person was found at the given
	 *                                        address
	 * @throws MedicalRecordNotFoundException if the medical record of a person was
	 *                                        not found
	 */
	List<DTOChildAlert> getChildAlert(String address) throws PersonNotFoundException, MedicalRecordNotFoundException;

}

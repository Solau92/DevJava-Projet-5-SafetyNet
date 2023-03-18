package com.safetynet.safetynetalerts.service;

import java.util.List;

import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOPersonInfo;

public interface IURLPersonInfoService {

	/**
	 * Returns a list of objects corresponding to a person and his name, address,
	 * age, email and his medical history (medication and dosage, allergies), given
	 * a firstname and a lastname.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return a list of DTOPersonInfo
	 * @throws PersonNotFoundException        if no person was found with the
	 *                                        firstname and lastname given
	 * @throws MedicalRecordNotFoundException if no medical record was found for one
	 *                                        person
	 */
	List<DTOPersonInfo> getPersonInfo(String firstName, String lastName)
			throws PersonNotFoundException, MedicalRecordNotFoundException;

}

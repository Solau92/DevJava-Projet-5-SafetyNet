package com.safetynet.safetynetalerts.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOPersonInfo;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

@Service
public class URLPersonInfoService implements IURLPersonInfoService {

	private final IPersonService personService;

	private final IMedicalRecordService medicalRecordService;

	public URLPersonInfoService(IPersonService personService, IMedicalRecordService medicalRecordService) {
		this.personService = personService;
		this.medicalRecordService = medicalRecordService;
	}

	/**
	 * Returns a list of objects corresponding to a person and his name, address,
	 * age, email and his medical history (medication and dosage, allergies), given
	 * a firstname and a lastname.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return a list of DTOPersonInfo
	 * @throws PersonNotFoundException        if no person was found in the
	 *                                        repository with the firstname and
	 *                                        lastname given
	 * @throws MedicalRecordNotFoundException if no medical record was found in the
	 *                                        repository for one person
	 */
	@Override
	public List<DTOPersonInfo> getPersonInfo(String firstName, String lastName)
			throws PersonNotFoundException, MedicalRecordNotFoundException {

		List<DTOPersonInfo> personsInfoList = new ArrayList<>();

		// List of persons with the given firstname and lastname
		List<Person> personsList = personService.getPersonsByFirstNameAndLastName(firstName, lastName);

		// List of medical records corresponding to the given firstname and lastname 
		List<MedicalRecord> medicalRecordsList = medicalRecordService.getMedicalRecordsByFirstNameAndLastName(firstName,
				lastName);

		for (Person p : personsList) {
			for (MedicalRecord mr : medicalRecordsList) {
				if (p.getFirstName().equals(mr.getFirstName()) && p.getLastName().equals(mr.getLastName())) {
					// Create a DTOPersonInfo
					DTOPersonInfo dtoPersonInfo = new DTOPersonInfo();
					dtoPersonInfo.setLastName(lastName);
					dtoPersonInfo.setAddress(p.getAddress());
					dtoPersonInfo.setAge(ChronoUnit.YEARS.between(mr.getBirthdate(), LocalDate.now()));
					dtoPersonInfo.setEmail(p.getEmail());
					dtoPersonInfo.setMedications(mr.getMedications());
					dtoPersonInfo.setAllergies(mr.getAllergies());
					// Add the DTOPersonInfo to the list
					personsInfoList.add(dtoPersonInfo);
				}
			}
		}

		return personsInfoList;
	}

}

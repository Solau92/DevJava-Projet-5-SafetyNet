package com.safetynet.safetynetalerts.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOChildAlert;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.PersonsRepository;

@Service
public class URLChildAlertService implements IURLChildAlertService {

	private final IPersonService personService;

	private final IMedicalRecordService medicalRecordService;

	public URLChildAlertService(IPersonService personService, IMedicalRecordService medicalRecordService) {
		this.personService = personService;
		this.medicalRecordService = medicalRecordService;
	}

	/**
	 * Returns a list of a list of children (person 18 years old or under) living at
	 * a given address, the list must include child's firstname, lastname, age, and
	 * a list of his household members.
	 * 
	 * @param address
	 * @return a list of DTOChildAlert
	 * @throws PersonNotFoundException        if no person was found in the
	 *                                        repository at the given address
	 * @throws MedicalRecordNotFoundException if the medical record of a person was
	 *                                        not found in the repository
	 */                                        
	@Override
	public List<DTOChildAlert> getChildAlert(String address)
			throws PersonNotFoundException, MedicalRecordNotFoundException {

		List<DTOChildAlert> childAlertList = new ArrayList<>();

		// List of persons living at the given address
		List<Person> personList = personService.getPersonsByAddress(address);

		// For each person of the list 
		for (Person p : personList) {

			// Get the medical record corresponding
			List<MedicalRecord> medicalRecords = medicalRecordService
					.getMedicalRecordsByFirstNameAndLastName(p.getFirstName(), p.getLastName());
 
			// If the person is years old or under
			if (!medicalRecordService.isPersonAdult(p.getFirstName(), p.getLastName())) {

				// Create a DTOChildAlert and sets the attributes with the informations of the Person and the MedicalRecord
				DTOChildAlert dTOChild = new DTOChildAlert();
				dTOChild.setFirstName(p.getFirstName());
				dTOChild.setLastName(p.getLastName());
				dTOChild.setAge(ChronoUnit.YEARS.between(medicalRecords.get(0).getBirthdate(), LocalDate.now()));

				// Creates the list of family members

				List<Person> familyMembers = personService.getPersonsByLastNameAndAddress(p.getLastName(),
						p.getAddress());

				// Remove the child from the list
				familyMembers.remove(p);

				// Set the list of family members of the DTOChildAlert
				dTOChild.setFamilyMembers(familyMembers);
				
				// Add this DTOChildAlert to the list of all DTOChildAlert 
				childAlertList.add(dTOChild);
			}

		}

		return childAlertList;
	}

}

package com.safetynet.safetynetalerts.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOFlood;
import com.safetynet.safetynetalerts.model.DTOFloodPerson;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

@Service
public class URLFloodService implements IURLFloodService {

	private final IPersonService personService;

	private final IFirestationService firestationService;

	private final IMedicalRecordService medicalRecordService;

	public URLFloodService(IPersonService personService, IFirestationService firestationService,
			IMedicalRecordService medicalRecordService) {
		this.personService = personService;
		this.firestationService = firestationService;
		this.medicalRecordService = medicalRecordService;
	}

	/**
	 * Returns a list of families living in the area of a given firestation ; the
	 * families must be grouped by address, and for each inhabitant you must have
	 * the lastname, the phone number, the age, and the medical history (medication,
	 * dosage and allergies).
	 * 
	 * @param stationIdList
	 * @return a list of DTOFlood
	 * @throws PersonNotFoundException        if no person was found in the
	 *                                        repository at one address
	 * @throws MedicalRecordNotFoundException if the medical record of a person is
	 *                                        not found in the repository
	 * @throws FirestationNotFoundException   if no address was found in the
	 *                                        repository for the given firestation
	 */
	@Override
	public List<DTOFlood> getFlood(List<Integer> stationIdList)
			throws PersonNotFoundException, MedicalRecordNotFoundException, FirestationNotFoundException {

		List<DTOFlood> dtoFloodList = new ArrayList<>();

		List<String> addressesList = new ArrayList<>();

		// For each firestation id
		for (Integer i : stationIdList) {

			// List of all addresses corresponding to the station
			List<String> address = firestationService.getAddressesWithId(i);
			addressesList.addAll(address);

			// For each address, get the list of families (DTOFloodFamily)
			for (String s : addressesList) {

				// For each address, I have a DTOFlood <adresse, families>
				DTOFlood dtoFlood = new DTOFlood();
				// Set the address of the DTOFlood
				dtoFlood.setAddress(s);

				// List of persons at the given address
				List<Person> personsList = personService.getPersonsByAddress(s);

				// For each person in the previous list
				for (Person p : personsList) {

					// If the family does not exist, create it
					if (!dtoFlood.getFamilyList().containsKey(p.getLastName())) {
						dtoFlood.getFamilyList().put(p.getLastName(), new ArrayList<>());
					}

					// In both cas, add the person in the list

					// Create a DTOFloodPerson, get his medical record, and sets attributes
					DTOFloodPerson dtoPerson = new DTOFloodPerson();
					dtoPerson.setLastName(p.getLastName());
					dtoPerson.setPhone(p.getPhone());

					List<MedicalRecord> medicalRecords = medicalRecordService
							.getMedicalRecordsByFirstNameAndLastName(p.getFirstName(), p.getLastName());
					dtoPerson.setAge(ChronoUnit.YEARS.between(medicalRecords.get(0).getBirthdate(), LocalDate.now()));
					dtoPerson.setAllergies(medicalRecords.get(0).getAllergies());
					dtoPerson.setMedications(medicalRecords.get(0).getMedications());

					// Add the DTOFloodPerson to the family
					dtoFlood.getFamilyList().get(p.getLastName()).add(dtoPerson);

				}

				// At the end of the loop, all persons at this address are in the list, I add my
				// DTOFlood to the list
				dtoFloodList.add(dtoFlood);
			}
		}

		return dtoFloodList;
	}

}

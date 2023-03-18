package com.safetynet.safetynetalerts.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOFire;
import com.safetynet.safetynetalerts.model.DTOFirePerson;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

@Service
public class URLFireService implements IURLFireService {

	private final IPersonService personService;

	private final IFirestationService firestationService;

	private final IMedicalRecordService medicalRecordService;

	public URLFireService(IPersonService personService, IFirestationService firestationService,
			IMedicalRecordService medicalRecordService) {
		this.personService = personService;
		this.firestationService = firestationService;
		this.medicalRecordService = medicalRecordService;
	}

	/**
	 * Returns an object including a list of inhabitants living at the given
	 * address, and the firestation id corresponding.
	 * 
	 * @param address
	 * @return a DTOFire
	 * @throws PersonNotFoundException        if no person was found in the
	 *                                        repository at the given address
	 * @throws MedicalRecordNotFoundException if the medical record was not found in
	 *                                        the repository for one person
	 * @throws FirestationNotFoundException   if no id was found in the repository
	 *                                        for the firestation corresponding to
	 *                                        the given address
	 */
	@Override
	public DTOFire getFire(String address)
			throws PersonNotFoundException, MedicalRecordNotFoundException, FirestationNotFoundException {

		DTOFire dtoFire = new DTOFire();
		List<DTOFirePerson> firePersonsList = new ArrayList<>();

		// List of persons living at a given address
		List<Person> personsList = personService.getPersonsByAddress(address);

		// For each person in the list
		for (Person p : personsList) {

			// Get his medical record
			List<MedicalRecord> medicalRecord = medicalRecordService
					.getMedicalRecordsByFirstNameAndLastName(p.getFirstName(), p.getLastName());

			// Create a DTOFirePerson and sets the attributs with the person and his medical
			// record attributs
			DTOFirePerson dTOPerson = new DTOFirePerson();
			dTOPerson.setLastName(p.getLastName());
			dTOPerson.setPhone(p.getPhone());
			dTOPerson.setAge(ChronoUnit.YEARS.between(medicalRecord.get(0).getBirthdate(), LocalDate.now()));
			dTOPerson.setMedications(medicalRecord.get(0).getMedications());
			dTOPerson.setAllergies(medicalRecord.get(0).getAllergies());
			// Add the DTOFirePerson the the firePersonsList 
			firePersonsList.add(dTOPerson);
		}

		// Set the firePersonsList of the DTOFire 
		dtoFire.setPersonsInBuilding(firePersonsList);

		// Set the station id of the DTOFire
		dtoFire.setStationId(firestationService.getIdWithAddress(address));

		return dtoFire;
	}

}

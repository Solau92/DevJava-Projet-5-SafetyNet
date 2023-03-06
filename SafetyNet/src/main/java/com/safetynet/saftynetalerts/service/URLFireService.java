package com.safetynet.saftynetalerts.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.exception.FirestationNotFoundException;
import com.safetynet.saftynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.saftynetalerts.exception.MoreThanOneMedicalRecordFoundException;
import com.safetynet.saftynetalerts.exception.PersonNotFoundException;
import com.safetynet.saftynetalerts.model.DTOFire;
import com.safetynet.saftynetalerts.model.DTOFirePerson;
import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.model.Person;

@Service
public class URLFireService implements IURLFireService {

	private final IPersonService personService;
	
	private final IFirestationService firestationService;
	
	private final IMedicalRecordService medicalRecordService;

	public URLFireService(IPersonService personService, IFirestationService firestationService, IMedicalRecordService medicalRecordService) {
		this.personService = personService;
		this.firestationService = firestationService;
		this.medicalRecordService = medicalRecordService;
	}
	
	@Override
	public DTOFire getFire(String address) throws PersonNotFoundException, MedicalRecordNotFoundException, MoreThanOneMedicalRecordFoundException, FirestationNotFoundException {
		
		DTOFire dtoFire = new DTOFire();
		List<DTOFirePerson> firePersonsList = new ArrayList<DTOFirePerson>();
		
		// Récup liste personnes à une adresse
		List<Person> personsList = personService.getPersonsByAddress(address);		
		
		// Ajout éléments dossier médical 
		for (Person p : personsList) {
			
			List<MedicalRecord> medicalRecord = medicalRecordService.getMedicalRecordsByFirstNameAndLastName(p.getFirstName(), p.getLastName());
			
			DTOFirePerson dTOPerson = new DTOFirePerson();
			dTOPerson.setLastName(p.getLastName());
			dTOPerson.setPhone(p.getPhone());
			dTOPerson.setAge(ChronoUnit.YEARS.between(medicalRecord.get(0).getBirthdate(), LocalDate.now()));
			dTOPerson.setMedications(medicalRecord.get(0).getMedications());
			dTOPerson.setAllergies(medicalRecord.get(0).getAllergies());
			firePersonsList.add(dTOPerson);			
		}
		
		dtoFire.setPersonsInBuilding(firePersonsList);
		
		// Num caserne 
		dtoFire.setStationId(firestationService.getIdWithAddress(address));

		return dtoFire;
	}
	



}

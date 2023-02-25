package com.safetynet.saftynetalerts.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.model.DTOFire;
import com.safetynet.saftynetalerts.model.DTOFirePerson;
import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.model.Person;

@Service
public class URLFireService implements IURLFireService {

	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IFirestationService firestationService;
	
	@Autowired
	private IMedicalRecordService medicalRecordService;

	@Override
	public DTOFire getFire(String address) {
		
		DTOFire dtoFire = new DTOFire();
		List<DTOFirePerson> firePersonsList = new ArrayList<DTOFirePerson>();
		
		// Récup liste personnes à une adresse
		List<Person> personsList = personService.getPersonsByAddress(address);		
		
		// Ajout éléments dossier médical 
		for (Person p : personsList) {
			
			MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordByFirstNameAndLastName(p.getFirstName(), p.getLastName());
			DTOFirePerson DTOPerson = new DTOFirePerson();
			DTOPerson.setLastName(address);
			DTOPerson.setPhone(p.getPhone());
			DTOPerson.setAge(ChronoUnit.YEARS.between(medicalRecord.getBirthdate(), LocalDate.now()));
			DTOPerson.setMedications(medicalRecord.getMedications());
			DTOPerson.setAllergies(medicalRecord.getAllergies());
			firePersonsList.add(DTOPerson);			
		}
		
		dtoFire.setPersonsInBuilding(firePersonsList);
		
		// Num caserne 
		dtoFire.setStationId(firestationService.getIdWithAddress(address));

		return dtoFire;
	}
	



}

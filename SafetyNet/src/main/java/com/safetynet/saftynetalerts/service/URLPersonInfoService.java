package com.safetynet.saftynetalerts.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.saftynetalerts.exception.PersonNotFoundException;
import com.safetynet.saftynetalerts.model.DTOPersonInfo;
import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.model.Person;

@Service
public class URLPersonInfoService implements IURLPersonInfoService {

	private final IPersonService personService;

	private final IMedicalRecordService medicalRecordService;
	
	public URLPersonInfoService(IPersonService personService, IMedicalRecordService medicalRecordService) {
		this.personService = personService;
		this.medicalRecordService = medicalRecordService;
	}

	@Override
	public List<DTOPersonInfo> getPersonInfo(String firstName, String lastName) throws PersonNotFoundException, MedicalRecordNotFoundException {

		List<DTOPersonInfo> personsInfoList = new ArrayList<>();
		
		// Je récupère la liste des personnes concernées 
		List<Person> personsList = personService.getPersonsByFirstNameAndLastName(firstName, lastName);
		
		// Je récupère la liste des medicalRecords 
		List<MedicalRecord> medicalRecordsList = medicalRecordService.getMedicalRecordsByFirstNameAndLastName(firstName, lastName);
		
		// Je construis mon DTO 
		for(Person p : personsList) {		
			for(MedicalRecord mr : medicalRecordsList) {
				if(p.getFirstName().equals(mr.getFirstName()) && p.getLastName().equals(mr.getLastName())) {
					DTOPersonInfo dtoPersonInfo = new DTOPersonInfo();
					dtoPersonInfo.setLastName(lastName);
					dtoPersonInfo.setAddress(p.getAddress());
					dtoPersonInfo.setAge(ChronoUnit.YEARS.between(mr.getBirthdate(), LocalDate.now()));
					dtoPersonInfo.setEmail(p.getEmail());
					dtoPersonInfo.setMedications(mr.getMedications());
					dtoPersonInfo.setAllergies(mr.getAllergies());
					personsInfoList.add(dtoPersonInfo);
				}
			}
		}
		
		/*
		 * personsList.stream().map(person -> { MedicalRecord record =
		 * medicalService.getMedicalRecordForAPerson(person); return
		 * DTOPErsonInfoFactory.makeDto(person, record); })
		 */
				
		return personsInfoList;
	}

}

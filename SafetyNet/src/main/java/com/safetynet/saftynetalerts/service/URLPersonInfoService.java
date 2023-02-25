package com.safetynet.saftynetalerts.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.model.DTOPersonInfo;
import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.model.Person;

@Service
public class URLPersonInfoService implements IURLPersonInfoService {

	@Autowired
	private PersonService personService;

	@Autowired
	private MedicalRecordService medicalRecordService;

	@Override
	public List<DTOPersonInfo> getPersonInfo(String firstName, String lastName) {

		List<DTOPersonInfo> personsInfoList = new ArrayList<DTOPersonInfo>();
		
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
		
		return personsInfoList;
	}

}

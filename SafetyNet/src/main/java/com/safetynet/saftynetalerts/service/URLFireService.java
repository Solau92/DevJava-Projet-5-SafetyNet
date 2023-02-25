package com.safetynet.saftynetalerts.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.safetynet.saftynetalerts.model.DTOFire;

public class URLFireService implements IURLFireService {

	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IFirestationService firestationService;
	
	@Autowired
	private IMedicalRecordService medicalRecordService;
	
	@Override
	public DTOFire getFire(String address) {
		DTOFire DTOfire = new DTOFire();
		
		// List<DTOFirePerson //
		
		// ID station //
		
		
		
		return DTOfire;
	}


}

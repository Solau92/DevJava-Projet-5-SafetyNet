package com.safetynet.safetynetalerts.service;

import java.util.List;

import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOPersonInfo;

public interface IURLPersonInfoService {
	
	List<DTOPersonInfo> getPersonInfo(String firstName, String lastName) throws PersonNotFoundException, MedicalRecordNotFoundException;

}

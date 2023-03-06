package com.safetynet.saftynetalerts.service;

import java.util.List;

import com.safetynet.saftynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.saftynetalerts.exception.MoreThanOneMedicalRecordFoundException;
import com.safetynet.saftynetalerts.exception.MoreThanOnePersonFoundException;
import com.safetynet.saftynetalerts.exception.PersonNotFoundException;
import com.safetynet.saftynetalerts.model.DTOPersonInfo;

public interface IURLPersonInfoService {
	
	List<DTOPersonInfo> getPersonInfo(String firstName, String lastName) throws PersonNotFoundException, MedicalRecordNotFoundException, MoreThanOneMedicalRecordFoundException, MoreThanOnePersonFoundException;

}

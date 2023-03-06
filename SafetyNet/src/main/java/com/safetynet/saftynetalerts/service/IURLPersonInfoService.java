package com.safetynet.saftynetalerts.service;

import java.util.List;

import com.safetynet.saftynetalerts.exception.PersonNotFoundException;
import com.safetynet.saftynetalerts.model.DTOPersonInfo;

public interface IURLPersonInfoService {
	
	List<DTOPersonInfo> getPersonInfo(String firstName, String lastName) throws PersonNotFoundException;

}

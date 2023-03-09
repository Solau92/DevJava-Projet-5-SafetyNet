package com.safetynet.saftynetalerts.service;

import java.util.List;

import com.safetynet.saftynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.saftynetalerts.exception.PersonNotFoundException;
import com.safetynet.saftynetalerts.model.DTOChildAlert;

public interface IURLChildAlertService {

	List<DTOChildAlert> getChildAlert(String address) throws PersonNotFoundException, MedicalRecordNotFoundException;

}

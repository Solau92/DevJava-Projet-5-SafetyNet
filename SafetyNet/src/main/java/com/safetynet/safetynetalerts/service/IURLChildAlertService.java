package com.safetynet.safetynetalerts.service;

import java.util.List;

import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOChildAlert;

public interface IURLChildAlertService {

	List<DTOChildAlert> getChildAlert(String address) throws PersonNotFoundException, MedicalRecordNotFoundException;

}

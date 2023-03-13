package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOFire;

public interface IURLFireService {

	DTOFire getFire(String address) throws PersonNotFoundException, MedicalRecordNotFoundException, FirestationNotFoundException;

}

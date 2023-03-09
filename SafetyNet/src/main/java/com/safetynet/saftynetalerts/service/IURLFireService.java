package com.safetynet.saftynetalerts.service;

import com.safetynet.saftynetalerts.exception.FirestationNotFoundException;
import com.safetynet.saftynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.saftynetalerts.exception.PersonNotFoundException;
import com.safetynet.saftynetalerts.model.DTOFire;

public interface IURLFireService {

	DTOFire getFire(String address) throws PersonNotFoundException, MedicalRecordNotFoundException, FirestationNotFoundException;

}

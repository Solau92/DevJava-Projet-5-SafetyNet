package com.safetynet.saftynetalerts.service;

import com.safetynet.saftynetalerts.exception.FirestationNotFoundException;
import com.safetynet.saftynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.saftynetalerts.exception.PersonNotFoundException;
import com.safetynet.saftynetalerts.model.DTOFirestation;

public interface IURLFirestationService {

	DTOFirestation getFirestation(int stationId) throws PersonNotFoundException, MedicalRecordNotFoundException, FirestationNotFoundException;

}

package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOFirestation;

public interface IURLFirestationService {

	DTOFirestation getFirestation(int stationId) throws PersonNotFoundException, MedicalRecordNotFoundException, FirestationNotFoundException;

}

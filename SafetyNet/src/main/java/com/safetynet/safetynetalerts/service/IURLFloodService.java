package com.safetynet.safetynetalerts.service;

import java.util.List;

import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOFlood;

public interface IURLFloodService {

	List<DTOFlood> getFlood(List<Integer> stationIdList) throws PersonNotFoundException, MedicalRecordNotFoundException, FirestationNotFoundException;

}

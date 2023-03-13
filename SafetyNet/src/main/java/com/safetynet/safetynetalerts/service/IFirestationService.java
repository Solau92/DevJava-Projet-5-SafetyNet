package com.safetynet.safetynetalerts.service;

import java.util.List;

import com.safetynet.safetynetalerts.exception.FirestationAlreadyExistsException;
import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.model.Firestation;

public interface IFirestationService {

	List<Firestation> getAllFirestations() throws FirestationNotFoundException;
	
	Firestation saveFirestation(Firestation firestation) throws FirestationAlreadyExistsException;
	
	Firestation updateFirestation(Firestation firestation) throws FirestationNotFoundException;
	
	void deleteFirestationByAddress(String adress) throws FirestationNotFoundException;
		
	List<String> getAddressesWithId(int stationId) throws FirestationNotFoundException;

	int getIdWithAddress(String address) throws FirestationNotFoundException;

	List<String> getAllAddresses();

}

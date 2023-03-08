package com.safetynet.saftynetalerts.service;

import java.util.List;

import com.safetynet.saftynetalerts.exception.FirestationAlreadyExistsException;
import com.safetynet.saftynetalerts.exception.FirestationNotFoundException;
import com.safetynet.saftynetalerts.model.FirestationSpot;

public interface IFirestationService {

	List<FirestationSpot> getAllFirestations() throws FirestationNotFoundException;
	
	FirestationSpot saveFirestation(FirestationSpot firestation) throws FirestationAlreadyExistsException;
	
	FirestationSpot updateFirestation(FirestationSpot firestation) throws FirestationNotFoundException;
	
	void deleteFirestationByAddress(String adress) throws FirestationNotFoundException;
	
//	void deleteFirestationById(int stationId);
	
	List<String> getAddressesWithId(int stationId) throws FirestationNotFoundException;

	int getIdWithAddress(String address) throws FirestationNotFoundException;

	List<String> getAllAddresses();

}

package com.safetynet.saftynetalerts.service;

import java.util.List;

import com.safetynet.saftynetalerts.model.FirestationSpot;

public interface IFirestationService {

	List<FirestationSpot> getAllFirestations();
	
	FirestationSpot saveFirestation(FirestationSpot firestation);
	
	FirestationSpot updateFirestation(FirestationSpot firestation);
	
	void deleteFirestationByAddress(String adress);
	
	void deleteFirestationById(int stationId);
	
	List<String> getAddressesWithId(int stationId);

	int getIdWithAddress(String address);


}

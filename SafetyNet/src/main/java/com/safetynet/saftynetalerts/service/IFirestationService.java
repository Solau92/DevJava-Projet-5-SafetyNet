package com.safetynet.saftynetalerts.service;

import java.util.List;

import com.safetynet.saftynetalerts.model.Firestation;

public interface IFirestationService {

	List<Firestation> getAllFirestations();
	
	Firestation saveFirestation(Firestation firestation);
	
	Firestation updateFirestation(Firestation firestation);
	
	void deleteFirestationByAddress(String adress);
	
	void deleteFirestationById(int stationId);
	
	List<String> getAddressesWithId(int stationId);


}

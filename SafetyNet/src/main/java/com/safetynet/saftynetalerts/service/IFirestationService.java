package com.safetynet.saftynetalerts.service;

import java.util.List;

import com.safetynet.saftynetalerts.model.Firestation;

public interface IFirestationService {

	public List<Firestation> getAllFirestations();
	
	public Firestation saveFirestation(Firestation firestation);
	
	public Firestation updateFirestation(Firestation firestation);
	
	public void deleteFirestationByAddress(String adress);
	
	public void deleteFirestationById(int stationId);

}

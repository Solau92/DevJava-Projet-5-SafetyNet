package com.safetynet.saftynetalerts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.model.FirestationSpot;
import com.safetynet.saftynetalerts.repository.FirestationsRepository;

@Service
public class FirestationService implements IFirestationService {
	
	/*
	 * @PostConstruct public void getToto() {
	 * 
	 * }
	 */

	private static FirestationsRepository firestations;
	
	public FirestationService(FirestationsRepository firestations) {
		this.firestations = firestations;
	}
	
	public List<FirestationSpot> getAllFirestations() {
		return firestations.getAllFirestations();
	}

	@Override
	public FirestationSpot saveFirestation(FirestationSpot firestation) {
		FirestationSpot firestationSaved = firestations.save(firestation);
		return firestationSaved;
	}

	@Override
	public FirestationSpot updateFirestation(FirestationSpot firestation) {
		FirestationSpot firestationUpdated = firestations.update(firestation);
		return firestationUpdated;
	}

	@Override
	public void deleteFirestationByAddress(String address) {
		firestations.deleteFirestation(address);
	}

	@Override
	public void deleteFirestationById(int stationId) {
		firestations.deleteFirestation(stationId);		
	}
	
	@Override
	public List<String> getAddressesWithId(int stationId) {
		return firestations.getAddressesWithId(stationId);
	}

	@Override
	public int getIdWithAddress(String address) {
		return firestations.getIdWithAddress(address);
	}
}

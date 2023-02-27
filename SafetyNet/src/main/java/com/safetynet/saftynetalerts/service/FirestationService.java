package com.safetynet.saftynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.model.FirestationSpot;
import com.safetynet.saftynetalerts.model.Person;
import com.safetynet.saftynetalerts.repository.FirestationsRepository;

import jakarta.annotation.PostConstruct;
import lombok.Data;

@Service
public class FirestationService implements IFirestationService {
	
	/*
	 * @PostConstruct public void getToto() {
	 * 
	 * }
	 */

	@Autowired
	private FirestationsRepository firestations;
	
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

package com.safetynet.saftynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.model.Firestation;
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
	
	public List<Firestation> getAllFirestations() {
		return firestations.getAllFirestations();
	}

	@Override
	public Firestation saveFirestation(Firestation firestation) {
		Firestation firestationSaved = firestations.save(firestation);
		return firestationSaved;
	}

	@Override
	public Firestation updateFirestation(Firestation firestation) {
		Firestation firestationUpdated = firestations.update(firestation);
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

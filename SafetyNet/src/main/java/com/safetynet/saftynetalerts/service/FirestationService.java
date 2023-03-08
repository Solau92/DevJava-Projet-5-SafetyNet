package com.safetynet.saftynetalerts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.exception.FirestationAlreadyExistsException;
import com.safetynet.saftynetalerts.exception.FirestationNotFoundException;
import com.safetynet.saftynetalerts.model.FirestationSpot;
import com.safetynet.saftynetalerts.repository.FirestationsRepository;

@Service
public class FirestationService implements IFirestationService {

	private final FirestationsRepository firestations;

	public FirestationService(FirestationsRepository firestations) {
		this.firestations = firestations;
	}

	public List<FirestationSpot> getAllFirestations() throws FirestationNotFoundException {
		List<FirestationSpot> list = firestations.getAllFirestations();
		if (list.isEmpty()) {
			throw new FirestationNotFoundException("No firestation found");
		}
		return list;
	}

	@Override
	public FirestationSpot saveFirestation(FirestationSpot firestation) throws FirestationAlreadyExistsException {
		List<String> addresses = firestations.getAllAddresses();
		if (addresses.contains(firestation.getAddress())) {
			throw new FirestationAlreadyExistsException("This address is already registred");
		} else {
			return firestations.save(firestation);
		}
	}

	@Override
	public FirestationSpot updateFirestation(FirestationSpot firestation) throws FirestationNotFoundException {
		List<String> addresses = firestations.getAllAddresses();
		if (!addresses.contains(firestation.getAddress())) {
			throw new FirestationNotFoundException("Firestation spot not found for this address");
		} else {
			return firestations.update(firestation);
		}
	}

	@Override
	public void deleteFirestationByAddress(String address) throws FirestationNotFoundException {
		List<String> addresses = firestations.getAllAddresses();
		if (!addresses.contains(address)) {
			throw new FirestationNotFoundException("Firestation spot not found for this address");
		} else {
		firestations.deleteFirestation(address);
		}
	}

//	// TODO : voir si je garde
//	@Override
//	public void deleteFirestationById(int stationId) {
//		firestations.deleteFirestation(stationId);
//	}

	
	@Override
	public List<String> getAddressesWithId(int stationId) throws FirestationNotFoundException {
		List<String> list = firestations.getAddressesWithId(stationId);
		if(list.isEmpty()) {
			throw new FirestationNotFoundException("No address found for the station : " + stationId);
		}
		return list;
	}

	@Override
	public int getIdWithAddress(String address) throws FirestationNotFoundException {
		int id = firestations.getIdWithAddress(address);
		if(id == 0) {
			throw new FirestationNotFoundException("Station id not found, address does not exists");
		}
		return id ;
	}

	@Override
	public List<String> getAllAddresses() {
		return firestations.getAllAddresses();
	}
}

package com.safetynet.saftynetalerts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.exception.FirestationAlreadyExistsException;
import com.safetynet.saftynetalerts.exception.FirestationNotFoundException;
import com.safetynet.saftynetalerts.model.Firestation;
import com.safetynet.saftynetalerts.repository.FirestationsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FirestationService implements IFirestationService {

	private final FirestationsRepository firestations;

	public FirestationService(FirestationsRepository firestations) {
		this.firestations = firestations;
	}

	public List<Firestation> getAllFirestations() throws FirestationNotFoundException {
		List<Firestation> list = firestations.getAllFirestations();
		if (list.isEmpty()) {
			log.error("No firestation found");
			throw new FirestationNotFoundException("No firestation found");
		}
		log.debug("Answer : ok, firestation list not empty");
		return list;
	}

	@Override
	public Firestation saveFirestation(Firestation firestation) throws FirestationAlreadyExistsException {
		List<String> addresses = firestations.getAllAddresses();
		if (addresses.contains(firestation.getAddress())) {
			log.error("Answer : The firestation corresponding to this address : {} already exists",
					firestation.getAddress());
			throw new FirestationAlreadyExistsException("This address is already registred");
		} else {
			log.info("Answer : The firestation corresponding to this address : {} was saved", firestation.getAddress());
			return firestations.save(firestation);
		}
	}

	@Override
	public Firestation updateFirestation(Firestation firestation) throws FirestationNotFoundException {
		List<String> addresses = firestations.getAllAddresses();
		if (!addresses.contains(firestation.getAddress())) {
			log.error("Answer : Firestation spot not found for this address : {}", firestation.getAddress());
			throw new FirestationNotFoundException("Firestation spot not found for this address");
		} else {
			log.info("Answer : Firestation for this address : {} was updated", firestation.getAddress());
			return firestations.update(firestation);
		}
	}

	@Override
	public void deleteFirestationByAddress(String address) throws FirestationNotFoundException {
		List<String> addresses = firestations.getAllAddresses();
		if (!addresses.contains(address)) {
			log.error("Answer : Firestation spot not found for this address : {}", address);
			throw new FirestationNotFoundException("Firestation spot not found for this address");
		} else {
			log.info("Answer : Firestation for this address : {} was deleted", address);
			firestations.deleteFirestation(address);
		}
	}

	@Override
	public List<String> getAddressesWithId(int stationId) throws FirestationNotFoundException {
		List<String> list = firestations.getAddressesWithId(stationId);
		if (list.isEmpty()) {
			log.error("Answer : No address found for the station : {}", stationId);
			throw new FirestationNotFoundException("No address found for the station : " + stationId);
		}
		log.debug("Answer : One or more adresses were found for this station id : {}", stationId);
		return list;
	}

	@Override
	public int getIdWithAddress(String address) throws FirestationNotFoundException {
		int id = firestations.getIdWithAddress(address);
		if (id == 0) {
			log.error("Answer : Firestation id not found, this address : {} does not exists", address);
			throw new FirestationNotFoundException("Station id not found, address does not exists");
		}
		log.debug("Answer : Firestation id found for this address : {}", address);
		return id;
	}

	// Quelles erreurs ? 
	@Override
	public List<String> getAllAddresses() {
		return firestations.getAllAddresses();
	}
}

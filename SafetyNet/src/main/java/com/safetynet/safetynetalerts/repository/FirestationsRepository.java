package com.safetynet.safetynetalerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.model.Firestation;

import lombok.Data;

@Repository
@Data
public class FirestationsRepository {

	private List<Firestation> firestations;

	public List<Firestation> getAllFirestations() {
		return firestations;
	}

	public Firestation save(Firestation firestation) {
		firestations.add(firestation);
		return firestation;
	}

	public Firestation update(Firestation firestation) {
		for (int i = 0; i < firestations.size(); i++) {
			if (firestation.getAddress().equalsIgnoreCase(firestations.get(i).getAddress())) {
				firestations.remove(firestations.get(i));
			}
		}
		firestations.add(firestation);
		return firestation;
	}

	public void deleteFirestation(String address) {
		for (int i = 0; i < firestations.size(); i++) {
			if (firestations.get(i).getAddress().equalsIgnoreCase(address)) {
				firestations.remove(firestations.get(i));
			}
		}
	}

	public void deleteFirestation(int stationId) {
		for (int i = 0; i < firestations.size(); i++) {
			if (firestations.get(i).getIdStation() == stationId) {
				firestations.remove(firestations.get(i));
			}
		}
	}

	public List<String> getAddressesWithId(int stationId) {
		List<String> addressesList = new ArrayList<>();
		for (Firestation f : firestations) {
			if (f.getIdStation() == stationId) {
				addressesList.add(f.getAddress());
			}
		}
		return addressesList;
	}

	public int getIdWithAddress(String address) {
		for (Firestation f : firestations) {
			if (f.getAddress().equals(address)) {
				return f.getIdStation();
			}
		}
		return 0;
	}

	public List<String> getAllAddresses() {
		List<String> addressesList = new ArrayList<>();
		for (Firestation f : firestations) {
			addressesList.add(f.getAddress());
		}
		return addressesList;
	}

}

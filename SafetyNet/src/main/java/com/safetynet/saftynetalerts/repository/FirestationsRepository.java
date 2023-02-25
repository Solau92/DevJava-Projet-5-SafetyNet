package com.safetynet.saftynetalerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.safetynet.saftynetalerts.model.Firestation;

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
			if (firestation.getAdress().equals(firestations.get(i).getAdress())) {
				firestations.remove(firestations.get(i));
			}
		}
		firestations.add(firestation);
		return firestation;
	}

	public void deleteFirestation(String address) {
		for (int i = 0; i < firestations.size(); i++) {
			if (firestations.get(i).getAdress().equals(address)) {
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
		List<String> addressesList = new ArrayList<String>();
		for (Firestation f : firestations) {
			if(f.getIdStation() == stationId) {
				addressesList.add(f.getAdress());
			}
		}
		return addressesList;
	}

}

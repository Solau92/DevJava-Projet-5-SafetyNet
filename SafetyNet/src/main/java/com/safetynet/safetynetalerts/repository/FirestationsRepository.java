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

	/**
	 * Returns the list of all firestations in repository.
	 * 
	 * @return the list of all firestations in repository
	 */
	public List<Firestation> getAllFirestations() {
		return firestations;
	}

	/**
	 * Saves in the repository the firestation given in parameter.
	 * 
	 * @param firestation
	 * @return the firestation saved
	 */
	public Firestation save(Firestation firestation) {
		firestations.add(firestation);
		return firestation;
	}

	/**
	 * Updates in the repository the firestation which has the same address as the
	 * one given in parameter.
	 * 
	 * @param firestation
	 * @return the firestation updated
	 */
	public Firestation update(Firestation firestation) {
		for (int i = 0; i < firestations.size(); i++) {
			if (firestation.getAddress().equalsIgnoreCase(firestations.get(i).getAddress())) {
				firestations.remove(firestations.get(i));
			}
		}
		firestations.add(firestation);
		return firestation;
	}

	/**
	 * Deletes in the repository the firestation which corresponds to the address
	 * given in parameter.
	 * 
	 * @param address
	 */
	public void deleteFirestation(String address) {
		for (int i = 0; i < firestations.size(); i++) {
			if (firestations.get(i).getAddress().equalsIgnoreCase(address)) {
				firestations.remove(firestations.get(i));
			}
		}
	}

	/**
	 * Deletes in the repository the firestations which correspond to the station id
	 * given in parameter.
	 * 
	 * @param stationId
	 */
	public void deleteFirestation(int stationId) {
		for (int i = 0; i < firestations.size(); i++) {
			if (firestations.get(i).getIdStation() == stationId) {
				firestations.remove(firestations.get(i));
			}
		}
	}

	/**
	 * Returns a list of the addresses corresponding to the given station id.
	 * 
	 * @param stationId
	 * @return a list of the addresses corresponding to the given station id or an
	 *         empty list if no address was found
	 */
	public List<String> getAddressesWithId(int stationId) {
		List<String> addressesList = new ArrayList<>();
		for (Firestation f : firestations) {
			if (f.getIdStation() == stationId) {
				addressesList.add(f.getAddress());
			}
		}
		return addressesList;
	}

	/**
	 * Returns the id of the station corresponding to the address given in
	 * parameter.
	 * 
	 * @param address
	 * @return the id of the station corresponding to the address given in
	 *         parameter, or 0 if the address is not found
	 */
	public int getIdWithAddress(String address) {
		for (Firestation f : firestations) {
			if (f.getAddress().equals(address)) {
				return f.getIdStation();
			}
		}
		return 0;
	}

	/**
	 * Returns all the addresses in the firestation repository.
	 * 
	 * @return all the addresses in the firestation repository or an empty list if
	 *         no address was found
	 */
	public List<String> getAllAddresses() {
		List<String> addressesList = new ArrayList<>();
		for (Firestation f : firestations) {
			addressesList.add(f.getAddress());
		}
		return addressesList;
	}

}

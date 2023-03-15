package com.safetynet.safetynetalerts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.exception.FirestationAlreadyExistsException;
import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.repository.FirestationsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FirestationService implements IFirestationService {

	private final FirestationsRepository firestations;

	public FirestationService(FirestationsRepository firestations) {
		this.firestations = firestations;
	}

	/**
	 * Returns the list of all firestations in repository.
	 * 
	 * @return the list of all firestations in repository
	 */
	public List<Firestation> getAllFirestations() throws FirestationNotFoundException {
		List<Firestation> list = firestations.getAllFirestations();
		if (list.isEmpty()) {
			log.error("No firestation found");
			throw new FirestationNotFoundException("No firestation found");
		}
		log.debug("Answer : ok, firestation list not empty");
		return list;
	}

	/**
	 * Saves in the repository the firestation given in parameter.
	 * @param firestation
	 * @return the firestation saved
	 * @throws FirestationAlreadyExistsException if a firestation is found in the repository for the same address
	 */
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

	/**
	 * Updates in the repository the firestation which has the same address as the
	 * one given in parameter.
	 * @param firestation
	 * @return the firestation updated
	 * @throws FirestationNotFoundException if no firestation was found in the repository with the corresponding address
	 */
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

	/**
	 * Deletes in the repository the firestation which corresponds to the address
	 * given in parameter.
	 * @param adress
	 * @throws FirestationNotFoundException if no firestation was found in the repository with the corresponding address
	 */
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

	/**
	 * Returns a list of the addresses corresponding to the given station id.
	 * 
	 * @param stationId
	 * @return a list of the addresses from the repository corresponding to the given station id or an
	 *         empty list if no address was found
	 * @throws FirestationNotFoundException if no firestation was found in the repository with the corresponding id
	 */
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

	/**
	 * Returns the id of the station corresponding to the address given in
	 * parameter.
	 * @param address
	 * @return the id of the station corresponding to the address given in
	 *         parameter
	 * @throws FirestationNotFoundException if no firestation was found in the respository corresponding with the addresse given
	 */
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

	/**
	 * Returns all the addresses listed for all the firestations in the repository.
	 * 
	 * @return all the addresses in the firestation repository or an empty list if
	 *         no address was found
	 */
	@Override
	public List<String> getAllAddresses() {
		return firestations.getAllAddresses();
	}
}

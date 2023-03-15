package com.safetynet.safetynetalerts.service;

import java.util.List;

import com.safetynet.safetynetalerts.exception.FirestationAlreadyExistsException;
import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.model.Firestation;

public interface IFirestationService {

	/**
	 * Returns the list of all firestations.
	 * 
	 * @return the list of all firestations
	 */
	List<Firestation> getAllFirestations() throws FirestationNotFoundException;
	
	/**
	 * Saves the firestation given in parameter.
	 * @param firestation
	 * @return the firestation saved
	 * @throws FirestationAlreadyExistsException if a firestation is found for the same address
	 */
	Firestation saveFirestation(Firestation firestation) throws FirestationAlreadyExistsException;
	
	/**
	 * Updates the firestation which has the same address as the
	 * one given in parameter.
	 * @param firestation
	 * @return the firestation updated
	 * @throws FirestationNotFoundException if no firestation was found with the corresponding address
	 */
	Firestation updateFirestation(Firestation firestation) throws FirestationNotFoundException;
	
	/**
	 * Deletes in the repository the firestation which corresponds to the address
	 * given in parameter.
	 * @param adress
	 * @throws FirestationNotFoundException if no firestation was found with the corresponding address
	 */
	void deleteFirestationByAddress(String adress) throws FirestationNotFoundException;
		
	/**
	 * Returns a list of the addresses corresponding to the given station id.
	 * 
	 * @param stationId
	 * @return a list of the addresses corresponding to the given station id or an
	 *         empty list if no address was found
	 * @throws FirestationNotFoundException if no firestation was found with the corresponding id
	 */
	List<String> getAddressesWithId(int stationId) throws FirestationNotFoundException;

	/**
	 * Returns the id of the station corresponding to the address given in
	 * parameter.
	 * @param address
	 * @return the id of the station corresponding to the address given in
	 *         parameter
	 * @throws FirestationNotFoundException if no firestation was found corresponding with the addresse given
	 */
	int getIdWithAddress(String address) throws FirestationNotFoundException;

	/**
	 * Returns all the addresses listed for all the firestations.
	 * 
	 * @return all the addresses in the firestation data source or an empty list if
	 *         no address was found
	 */
	List<String> getAllAddresses();

}

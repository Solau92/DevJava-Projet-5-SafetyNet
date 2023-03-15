package com.safetynet.safetynetalerts.service;

import java.util.List;

import com.safetynet.safetynetalerts.exception.MedicalRecordAlreadyExistsException;
import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.model.MedicalRecord;

public interface IMedicalRecordService {

	/**
	 * Returns the list of all medical records.
	 * 
	 * @return the list of all medical records
	 * @throws MedicalRecordNotFoundException if no medical record was found
	 */
	public List<MedicalRecord> getAllMedicalRecords() throws MedicalRecordNotFoundException;

	/**
	 * Saves the medical record given in parameter.
	 * 
	 * @param medicalRecord
	 * @return the medical record saved
	 * @throws MedicalRecordAlreadyExistsException if there's already one medical
	 *                                             record saved with the same
	 *                                             firstname and lastname
	 */
	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) throws MedicalRecordAlreadyExistsException;

	/**
	 * Updates the medical record whose firstname and lastname are the same as the
	 * one of the medical record given in parameter.
	 * 
	 * @param medicalRecord
	 * @return the medical record updated
	 * @throws MedicalRecordNotFoundException if no medical record was found with
	 *                                        the firstname and lastname given
	 */
	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) throws MedicalRecordNotFoundException;

	/**
	 * Deletes the medical record which has the firstname and lastname given in
	 * parameter.
	 * 
	 * @param firstName
	 * @param lastName
	 * @throws MedicalRecordNotFoundException if no medical record was found with
	 *                                        the firstname and lastname given
	 */
	public void deleteMedicalRecord(String firstName, String lastName) throws MedicalRecordNotFoundException;

	/**
	 * Calculates the age of a person corresponding to a medical record, and returns
	 * the fact that this person is or not over 18.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return false if the person is 18 years old or under, true otherwise
	 * @throws MedicalRecordNotFoundException if no medical record was found with
	 *                                        the firstname and lastname given
	 */
	boolean isPersonAdult(String firstName, String lastName) throws MedicalRecordNotFoundException;

	/**
	 * Returns a list of medical records, which correspond to a firstname and a
	 * lastname given in parameter.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return list of medical records, which correspond to a firstname and a
	 *         lastname given in parameter
	 * @throws MedicalRecordNotFoundException if no medical record was found with
	 *                                        the firstname and lastname given
	 */
	List<MedicalRecord> getMedicalRecordsByFirstNameAndLastName(String firstName, String lastName)
			throws MedicalRecordNotFoundException;

}

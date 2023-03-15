package com.safetynet.safetynetalerts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.exception.MedicalRecordAlreadyExistsException;
import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.repository.MedicalRecordsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicalRecordService implements IMedicalRecordService {

	private final MedicalRecordsRepository medicalRecords;

	private static final String MEDICAL_RECORD_NOT_FOUND = "Answer : {} {}'s medicalRecord was not found";

	public MedicalRecordService(MedicalRecordsRepository medicalRecords) {
		this.medicalRecords = medicalRecords;
	}

	/**
	 * Returns the list of all medical records of the repository.
	 * 
	 * @return the list of all medical records of the repository
	 * @throws MedicalRecordNotFoundException if no medical record was found in the repository

	 */
	public List<MedicalRecord> getAllMedicalRecords() throws MedicalRecordNotFoundException {
		List<MedicalRecord> list = medicalRecords.getAllMedicalRecords();
		if (list.isEmpty()) {
			log.error("Answer : No medicalRecord found");
			throw new MedicalRecordNotFoundException("No medical record found");
		}
		log.debug("Answer : ok, medicalRecords list not empty");
		return list;
	}

	/**
	 * Saves in the repository the medical record given in parameter.
	 * 
	 * @param medicalRecord
	 * @return the medical record saved
	 * @throws MedicalRecordAlreadyExistsException if there's already one medical
	 *                                             record in the repository with the
	 *                                             same firstname and lastname
	 */
	@Override
	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) throws MedicalRecordAlreadyExistsException {
		if (!medicalRecords
				.getMedicalRecordsByFirstNameAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName())
				.isEmpty()) {
			log.error("Answer : {} {}'s medicalRecord already exists", medicalRecord.getFirstName(),
					medicalRecord.getLastName());
			throw new MedicalRecordAlreadyExistsException(medicalRecord.getFirstName() + " "
					+ medicalRecord.getLastName() + "'s medical record already exists");
		} else {
			log.info("Answer : {} {}'s medicalRecord was saved", medicalRecord.getFirstName(),
					medicalRecord.getLastName());
			return medicalRecords.save(medicalRecord);
		}
	}

	/**
	 * Updates in the repository the medical record whose firstname and lastname are
	 * the same as the one of the medical record given in parameter.
	 * 
	 * @param medicalRecord
	 * @return the medical record updated
	 * @throws MedicalRecordNotFoundException if no medical record was found in the
	 *                                        repository with the firstname and
	 *                                        lastname given
	 */
	@Override
	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) throws MedicalRecordNotFoundException {
		if (medicalRecords
				.getMedicalRecordsByFirstNameAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName())
				.isEmpty()) {
			log.error(MEDICAL_RECORD_NOT_FOUND, medicalRecord.getFirstName(), medicalRecord.getLastName());
			throw new MedicalRecordNotFoundException("No medical record found");
		}
		log.info("Answer : {} {}'s medicalRecord was updated", medicalRecord.getFirstName(),
				medicalRecord.getLastName());
		return medicalRecords.update(medicalRecord);
	}

	/**
	 * Deletes in the repository the medical record which has the firstname and
	 * lastname given in parameter.
	 * 
	 * @param firstName
	 * @param lastName
	 * @throws MedicalRecordNotFoundException if no medical record was found in the
	 *                                        repository with the firstname and
	 *                                        lastname given
	 */
	@Override
	public void deleteMedicalRecord(String firstName, String lastName) throws MedicalRecordNotFoundException {
		if (medicalRecords.getMedicalRecordsByFirstNameAndLastName(firstName, lastName).isEmpty()) {
			log.error(MEDICAL_RECORD_NOT_FOUND, firstName, lastName);
			throw new MedicalRecordNotFoundException("No medical record found for " + firstName + " " + lastName);
		} else {
			log.info("Answer : {} {}'s medicalRecord was deleted", firstName, lastName);
			medicalRecords.deleteMedicalRecord(firstName, lastName);
		}
	}

	/**
	 * Calculates the age of a person corresponding to a medical record, and returns
	 * the fact that this person is or not over 18.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return false if the person is 18 years old or under, true otherwise
	 * @throws MedicalRecordNotFoundException if no medical record was found in the
	 *                                        repository with the firstname and
	 *                                        lastname given
	 */
	@Override
	public boolean isPersonAdult(String firstName, String lastName) throws MedicalRecordNotFoundException {
		if (medicalRecords.getMedicalRecordsByFirstNameAndLastName(firstName, lastName).isEmpty()) {
			log.error(MEDICAL_RECORD_NOT_FOUND, firstName, lastName);
			throw new MedicalRecordNotFoundException("No medical record found for " + firstName + " " + lastName);
		} else {
			log.debug("Answer : {} {}'s medicalRecord was found", firstName, lastName);
			return medicalRecords.isPersonAdult(firstName, lastName);
		}
	}

	/**
	 * Returns a list of the medical records found in the repository, which
	 * correspond to a firstname and a lastname given in parameter.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return list of medical records from the repository, which correspond to a
	 *         firstname and a lastname given in parameter
	 * @throws MedicalRecordNotFoundException if no medical record was found in the
	 *                                        repository with the firstname and
	 *                                        lastname given
	 */
	@Override
	public List<MedicalRecord> getMedicalRecordsByFirstNameAndLastName(String firstName, String lastName)
			throws MedicalRecordNotFoundException {
		List<MedicalRecord> list = medicalRecords.getMedicalRecordsByFirstNameAndLastName(firstName, lastName);
		if (list.isEmpty()) {
			log.error(MEDICAL_RECORD_NOT_FOUND, firstName, lastName);
			throw new MedicalRecordNotFoundException(firstName + " " + lastName + "'s medical record was not found");
		}
		log.debug("Answer : {} {}'s medicalRecord was found", firstName, lastName);
		return list;
	}

}

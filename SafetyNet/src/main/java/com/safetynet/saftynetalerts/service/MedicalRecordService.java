package com.safetynet.saftynetalerts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.exception.MedicalRecordAlreadyExistsException;
import com.safetynet.saftynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.repository.MedicalRecordsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicalRecordService implements IMedicalRecordService {

	private final MedicalRecordsRepository medicalRecords;

	public MedicalRecordService(MedicalRecordsRepository medicalRecords) {
		this.medicalRecords = medicalRecords;
	}

	public List<MedicalRecord> getAllMedicalRecords() throws MedicalRecordNotFoundException {
		List<MedicalRecord> list = medicalRecords.getAllMedicalRecords();
		if (list.isEmpty()) {
			log.error("Answer : No medicalRecord found");
			throw new MedicalRecordNotFoundException("No medical record found");
		}
		log.debug("Answer : ok, medicalRecords list not empty");
		return list;
	}

	@Override
	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) throws MedicalRecordAlreadyExistsException {
		if (!medicalRecords
				.getMedicalRecordsByFirstNameAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName())
				.isEmpty()) {
			log.error("Answer : {} {}'s medicalRecord already exists",  medicalRecord.getFirstName(), medicalRecord.getLastName());
			throw new MedicalRecordAlreadyExistsException(medicalRecord.getFirstName() + " "
					+ medicalRecord.getLastName() + "'s medical record already exists");
		} else {
			log.info("Answer : {} {}'s medicalRecord was saved", medicalRecord.getFirstName(), medicalRecord.getLastName());
			return medicalRecords.save(medicalRecord);
		}
	}

	@Override
	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) throws MedicalRecordNotFoundException {
		if (medicalRecords
				.getMedicalRecordsByFirstNameAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName())
				.isEmpty()) {
			log.error("Answer : {} {}'s medicalRecord was not found",  medicalRecord.getFirstName(), medicalRecord.getLastName());
			throw new MedicalRecordNotFoundException("No medical record found");
		}
		log.info("Answer : {} {}'s medicalRecord was updated",  medicalRecord.getFirstName(), medicalRecord.getLastName());
		return medicalRecords.update(medicalRecord);
	}

	@Override
	public void deleteMedicalRecord(String firstName, String lastName) throws MedicalRecordNotFoundException {
		if (medicalRecords.getMedicalRecordsByFirstNameAndLastName(firstName, lastName).isEmpty()) {
			log.error("Answer : {} {}'s medicalRecord was not found",  firstName, lastName);
			throw new MedicalRecordNotFoundException("No medical record found for " + firstName + " " + lastName);
		} else {
			log.info("Answer : {} {}'s medicalRecord was deleted", firstName, lastName);
			medicalRecords.deleteMedicalRecord(firstName, lastName);
		}
	}

	@Override
	public boolean isPersonAdult(String firstName, String lastName) throws MedicalRecordNotFoundException {
		if (medicalRecords.getMedicalRecordsByFirstNameAndLastName(firstName, lastName).isEmpty()) {
			log.error("Answer : {} {}'s medicalRecord was not found",  firstName, lastName);
			throw new MedicalRecordNotFoundException("No medical record found for " + firstName + " " + lastName);
		} else {
			log.debug("Answer : {} {}'s medicalRecord was found", firstName, lastName);
			return medicalRecords.isPersonAdult(firstName, lastName);
		}
	}

	@Override
	public List<MedicalRecord> getMedicalRecordsByFirstNameAndLastName(String firstName, String lastName)
			throws MedicalRecordNotFoundException {
		List<MedicalRecord> list = medicalRecords.getMedicalRecordsByFirstNameAndLastName(firstName, lastName);
		if (list.isEmpty()) {
			log.error("Answer : {} {}'s medicalRecord was not found",  firstName, lastName);
			throw new MedicalRecordNotFoundException(firstName + " " + lastName + "'s medical record was not found");
//		} else if (list.size() > 1) {
//			throw new MoreThanOneMedicalRecordFoundException("There's more than one medical record for " + firstName + " " + lastName);
		}
		log.debug("Answer : {} {}'s medicalRecord was found", firstName, lastName);
		return list;
	}

}

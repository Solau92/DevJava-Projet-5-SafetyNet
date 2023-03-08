package com.safetynet.saftynetalerts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.exception.MedicalRecordAlreadyExistsException;
import com.safetynet.saftynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.saftynetalerts.exception.MoreThanOneMedicalRecordFoundException;
import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.repository.MedicalRecordsRepository;

@Service
public class MedicalRecordService implements IMedicalRecordService {

	private final MedicalRecordsRepository medicalRecords;

	public MedicalRecordService(MedicalRecordsRepository medicalRecords) {
		this.medicalRecords = medicalRecords;
	}

	public List<MedicalRecord> getAllMedicalRecords() throws MedicalRecordNotFoundException {
		List<MedicalRecord> list = medicalRecords.getAllMedicalRecords();
		if (list.isEmpty()) {
			throw new MedicalRecordNotFoundException("No medical record found");
		}
		return list;
	}

	@Override
	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) throws MedicalRecordAlreadyExistsException {
		if (!medicalRecords
				.getMedicalRecordsByFirstNameAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName())
				.isEmpty()) {
			throw new MedicalRecordAlreadyExistsException(medicalRecord.getFirstName() + " "
					+ medicalRecord.getLastName() + "'s medical record already exists");
		} else {
			return medicalRecords.save(medicalRecord);
		}
	}

	@Override
	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) throws MedicalRecordNotFoundException {
		if (medicalRecords
				.getMedicalRecordsByFirstNameAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName())
				.isEmpty()) {
			throw new MedicalRecordNotFoundException("No medical record found");
		}
		return medicalRecords.update(medicalRecord);
	}

	@Override
	public void deleteMedicalRecord(String firstName, String lastName) throws MedicalRecordNotFoundException {
		if (medicalRecords.getMedicalRecordsByFirstNameAndLastName(firstName, lastName).isEmpty()) {
			throw new MedicalRecordNotFoundException("No medical record found for " + firstName + " " + lastName);
		} else {
			medicalRecords.deleteMedicalRecord(firstName, lastName);
		}
	}

	@Override
	public boolean isPersonAdult(String firstName, String lastName) throws MedicalRecordNotFoundException {
		if (medicalRecords.getMedicalRecordsByFirstNameAndLastName(firstName, lastName).isEmpty()) {
			throw new MedicalRecordNotFoundException("No medical record found for " + firstName + " " + lastName);
		} else {
			return medicalRecords.isPersonAdult(firstName, lastName);
		}
	}

	@Override
	public List<MedicalRecord> getMedicalRecordsByFirstNameAndLastName(String firstName, String lastName)
			throws MedicalRecordNotFoundException, MoreThanOneMedicalRecordFoundException {
		List<MedicalRecord> list = medicalRecords.getMedicalRecordsByFirstNameAndLastName(firstName, lastName);
		if (list.isEmpty()) {
			throw new MedicalRecordNotFoundException(firstName + " " + lastName + "'s medical record was not found");
//		} else if (list.size() > 1) {
//			throw new MoreThanOneMedicalRecordFoundException("There's more than one medical record for " + firstName + " " + lastName);
		}
		return list;
	}

}

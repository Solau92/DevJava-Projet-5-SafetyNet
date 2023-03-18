package com.safetynet.safetynetalerts.repository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.model.MedicalRecord;

import lombok.Data;

@Repository
@Data
public class MedicalRecordsRepository {

	private List<MedicalRecord> medicalRecords;

	/**
	 * Returns the list of all medical records.
	 * 
	 * @return the list of all medical records
	 */
	public List<MedicalRecord> getAllMedicalRecords() {
		return medicalRecords;
	}

	/**
	 * Saves in the medical records repository the medical record given in
	 * parameter.
	 * 
	 * @param medicalRecord
	 * @return the medical record saved
	 */
	public MedicalRecord save(MedicalRecord medicalRecord) {
		medicalRecords.add(medicalRecord);
		return medicalRecord;
	}

	/**
	 * Updates in the repository the medical record which has the same firstname and
	 * lastname as the one given in parameter.
	 * 
	 * @param medicalRecord
	 * @return the medical record updated
	 */
	public MedicalRecord update(MedicalRecord medicalRecord) {
		for (int i = 0; i < medicalRecords.size(); i++) {
			if (medicalRecords.get(i).getFirstName().equalsIgnoreCase(medicalRecord.getFirstName())
					&& medicalRecords.get(i).getLastName().equalsIgnoreCase(medicalRecord.getLastName())) {
				medicalRecords.remove(medicalRecords.get(i));
			}
		}
		medicalRecords.add(medicalRecord);
		return medicalRecord;
	}

	/**
	 * Deletes in the repository the medical record which has the firstname and
	 * lastname given in parameter.
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public void deleteMedicalRecord(String firstName, String lastName) {
		for (int i = 0; i < medicalRecords.size(); i++) {
			if (medicalRecords.get(i).getFirstName().equalsIgnoreCase(firstName)
					&& medicalRecords.get(i).getLastName().equalsIgnoreCase(lastName)) {
				medicalRecords.remove(medicalRecords.get(i));
			}
		}
	}

	/**
	 * Calculates the age of a person corresponding to a medical record, and returns
	 * the fact that this person is or not over 18.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return false if the person is 18 years old or under, true otherwise
	 */
	public boolean isPersonAdult(String firstName, String lastName) {
		for (MedicalRecord medicalRecord : medicalRecords) {
			if (medicalRecord.getFirstName().equalsIgnoreCase(firstName)
					&& medicalRecord.getLastName().equalsIgnoreCase(lastName)) {
				return ChronoUnit.YEARS.between(medicalRecord.getBirthdate(), LocalDate.now()) > 18;
			}
		}
		return true;
	}

	/**
	 * Returns a list of medical records, which correspond to a firstname and a
	 * lastname given in parameter.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return list of medical records, which correspond to a firstname and a
	 *         lastname given in parameter
	 */
	public List<MedicalRecord> getMedicalRecordsByFirstNameAndLastName(String firstName, String lastName) {
		List<MedicalRecord> medicalRecordsList = new ArrayList<>();
		for (MedicalRecord mr : medicalRecords) {
			if (mr.getFirstName().equalsIgnoreCase(firstName) && mr.getLastName().equalsIgnoreCase(lastName)) {
				medicalRecordsList.add(mr);
			}
		}
		return medicalRecordsList;
	}
}

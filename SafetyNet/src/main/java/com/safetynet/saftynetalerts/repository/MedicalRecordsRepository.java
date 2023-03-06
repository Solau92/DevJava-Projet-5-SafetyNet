package com.safetynet.saftynetalerts.repository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.saftynetalerts.model.MedicalRecord;
import lombok.Data;

@Repository
@Data
public class MedicalRecordsRepository {

	private List<MedicalRecord> medicalRecords;

	public List<MedicalRecord> getAllMedicalRecords() {
		return medicalRecords;
	}

	public MedicalRecord save(MedicalRecord medicalRecord) {
		medicalRecords.add(medicalRecord);
		return medicalRecord;
	}

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

	public void deleteMedicalRecord(String firstName, String lastName) {
		for (int i = 0; i < medicalRecords.size(); i++) {
			if (medicalRecords.get(i).getFirstName().equalsIgnoreCase(firstName)
					&& medicalRecords.get(i).getLastName().equalsIgnoreCase(lastName)) {
				medicalRecords.remove(medicalRecords.get(i));
			}
		}
	}

	public boolean isPersonAdult(String firstName, String lastName) {
		for (MedicalRecord medicalRecord : medicalRecords) {
			if (medicalRecord.getFirstName().equalsIgnoreCase(firstName) && medicalRecord.getLastName().equalsIgnoreCase(lastName)) {
				return ChronoUnit.YEARS.between(medicalRecord.getBirthdate(), LocalDate.now()) > 18;
			}
		}
		return true;
	}

	public List<MedicalRecord> getMedicalRecordsByFirstNameAndLastName(String firstName, String lastName) {
		List<MedicalRecord> medicalRecordsList = new ArrayList<MedicalRecord>();
		for (MedicalRecord mr : medicalRecords) {
			if (mr.getFirstName().equalsIgnoreCase(firstName) && mr.getLastName().equalsIgnoreCase(lastName)) {
				medicalRecordsList.add(mr);
			}
		}
		return medicalRecordsList;
	}
}

package com.safetynet.saftynetalerts.repository;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.safetynet.saftynetalerts.model.Firestation;
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
			if (medicalRecords.get(i).getFirstName().equals(medicalRecord.getFirstName())
					&& medicalRecords.get(i).getLastName().equals(medicalRecord.getLastName())) {
				medicalRecords.remove(medicalRecords.get(i));
			}
		}
		medicalRecords.add(medicalRecord);
		return medicalRecord;
	}

	public void deleteMedicalRecord(String firstName, String lastName) {
		for (int i = 0; i < medicalRecords.size(); i++) {
			if (medicalRecords.get(i).getFirstName().equals(firstName)
					&& medicalRecords.get(i).getLastName().equals(lastName)) {
				medicalRecords.remove(medicalRecords.get(i));
			}
		}
	}
}

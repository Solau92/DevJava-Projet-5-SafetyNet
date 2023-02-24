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

}

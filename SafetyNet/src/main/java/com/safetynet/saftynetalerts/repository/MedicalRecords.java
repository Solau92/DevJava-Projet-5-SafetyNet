package com.safetynet.saftynetalerts.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.safetynet.saftynetalerts.model.MedicalRecord;
import lombok.Data;

@Component
@Data
public class MedicalRecords {
	
	private List<MedicalRecord> medicalRecords;

	public List<MedicalRecord> getAllMedicalRecords() {
		return medicalRecords;
	}

}

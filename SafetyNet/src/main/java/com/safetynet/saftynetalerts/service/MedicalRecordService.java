package com.safetynet.saftynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.repository.MedicalRecords;

import lombok.Data;

@Data
@Service
public class MedicalRecordService {

	@Autowired
	private MedicalRecords medicalRecords;
	
	public List<MedicalRecord> getAllMedicalRecords() {
		return medicalRecords.getAllMedicalRecords();
	}

}

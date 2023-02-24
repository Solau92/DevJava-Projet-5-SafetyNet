package com.safetynet.saftynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.repository.MedicalRecordsRepository;

import lombok.Data;

@Service
public class MedicalRecordService {

	@Autowired
	private MedicalRecordsRepository medicalRecords;
	
	public List<MedicalRecord> getAllMedicalRecords() {
		return medicalRecords.getAllMedicalRecords();
	}

}

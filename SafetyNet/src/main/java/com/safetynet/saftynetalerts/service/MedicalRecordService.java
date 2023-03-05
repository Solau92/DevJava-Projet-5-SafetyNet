package com.safetynet.saftynetalerts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.repository.MedicalRecordsRepository;

@Service
public class MedicalRecordService implements IMedicalRecordService {

	private static MedicalRecordsRepository medicalRecords;
	
	public MedicalRecordService(MedicalRecordsRepository medicalRecords) {
		this.medicalRecords = medicalRecords;
	}
	
	public List<MedicalRecord> getAllMedicalRecords() {
		return medicalRecords.getAllMedicalRecords();
	}

	@Override
	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
		MedicalRecord medicalRecordSaved = medicalRecords.save(medicalRecord);
		return medicalRecordSaved;
	}

	@Override
	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
		MedicalRecord medicalRecordUpdated = medicalRecords.update(medicalRecord);
		return medicalRecordUpdated;
	}

	@Override
	public void deleteMedicalRecord(String firstName, String lastName) {
		medicalRecords.deleteMedicalRecord(firstName, lastName); 
	}
	
	@Override
	public boolean isPersonAdult(String firstName, String lastName) {
		return medicalRecords.isPersonAdult(firstName, lastName);
	}
	
	// Gère pas le cas où plusiuers : voir où est utilisé 
	// TODO 
	@Override
	public MedicalRecord getMedicalRecordByFirstNameAndLastName(String firstName, String lastName) {
		return medicalRecords.getMedicalRecordByFirstNameAndLastName(firstName, lastName);
	}

	@Override
	public List<MedicalRecord> getMedicalRecordsByFirstNameAndLastName(String firstName, String lastName) {
		return medicalRecords.getMedicalRecordsByFirstNameAndLastName(firstName, lastName);
	}

}

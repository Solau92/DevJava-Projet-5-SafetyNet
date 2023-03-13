package com.safetynet.safetynetalerts.service;

import java.util.List;

import com.safetynet.safetynetalerts.exception.MedicalRecordAlreadyExistsException;
import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.model.MedicalRecord;

public interface IMedicalRecordService {

	public List<MedicalRecord> getAllMedicalRecords() throws MedicalRecordNotFoundException;
	
	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) throws MedicalRecordAlreadyExistsException;
	
	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) throws MedicalRecordNotFoundException;

	public void deleteMedicalRecord(String firstName, String lastName) throws MedicalRecordNotFoundException;

	boolean isPersonAdult(String firstName, String lastName) throws MedicalRecordNotFoundException;

	List<MedicalRecord> getMedicalRecordsByFirstNameAndLastName(String firstName, String lastName) throws MedicalRecordNotFoundException;

}

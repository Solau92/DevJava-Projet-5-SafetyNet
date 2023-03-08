package com.safetynet.saftynetalerts.service;

import java.util.List;

import com.safetynet.saftynetalerts.exception.MedicalRecordAlreadyExistsException;
import com.safetynet.saftynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.saftynetalerts.exception.MoreThanOneMedicalRecordFoundException;
import com.safetynet.saftynetalerts.model.MedicalRecord;

public interface IMedicalRecordService {

	public List<MedicalRecord> getAllMedicalRecords() throws MedicalRecordNotFoundException;
	
	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) throws MedicalRecordAlreadyExistsException;
	
	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) throws MedicalRecordNotFoundException;

	public void deleteMedicalRecord(String firstName, String lastName) throws MedicalRecordNotFoundException;

	boolean isPersonAdult(String firstName, String lastName) throws MedicalRecordNotFoundException;

	List<MedicalRecord> getMedicalRecordsByFirstNameAndLastName(String firstName, String lastName) throws MedicalRecordNotFoundException, MoreThanOneMedicalRecordFoundException;

}

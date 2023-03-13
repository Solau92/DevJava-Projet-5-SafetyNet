package com.safetynet.safetynetalerts.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.repository.FirestationsRepository;
import com.safetynet.safetynetalerts.repository.MedicalRecordsRepository;
import com.safetynet.safetynetalerts.repository.PersonsRepository;
import com.safetynet.safetynetalerts.repository.readers.IDataFirestationsReader;
import com.safetynet.safetynetalerts.repository.readers.IDataMedicalRecordsReader;
import com.safetynet.safetynetalerts.repository.readers.IDataPersonsReader;
import com.safetynet.safetynetalerts.repository.readers.JSONFirestationsDataReader;
import com.safetynet.safetynetalerts.repository.readers.JSONMedicalRecordsDataReader;
import com.safetynet.safetynetalerts.repository.readers.JSONPersonsDataReader;

import jakarta.annotation.PostConstruct;

@Configuration
@Service
public class ObjectsCreator implements IObjectsCreator {

	private final PersonsRepository personsList;
	
	private final FirestationsRepository firestationsList;
	
	private final MedicalRecordsRepository medicalRecordsList;
	
	@Value("${com.safetynet.saftynetalerts.path}")
	private String filePath;
	
	public ObjectsCreator(PersonsRepository personsList, FirestationsRepository firestationsList, MedicalRecordsRepository medicalRecordsList) {
		this.personsList = personsList;
		this.firestationsList = firestationsList;
		this.medicalRecordsList = medicalRecordsList;
	}

	@Override
	@PostConstruct
	public void run() throws IOException {
		
		// Persons //
		IDataPersonsReader pReader = new JSONPersonsDataReader(filePath);
		personsList.setPersons(pReader.readPersons());
		
		// Firestations //
		IDataFirestationsReader fsReader = new JSONFirestationsDataReader(filePath);
		firestationsList.setFirestations(fsReader.readFirestations());
		
		// MedicalRecords //
		IDataMedicalRecordsReader mrReader = new JSONMedicalRecordsDataReader(filePath);
		medicalRecordsList.setMedicalRecords(mrReader.readMedicalRecords());
	}

}
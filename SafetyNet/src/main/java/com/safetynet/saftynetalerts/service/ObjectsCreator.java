package com.safetynet.saftynetalerts.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.repository.FirestationsRepository;
import com.safetynet.saftynetalerts.repository.MedicalRecordsRepository;
import com.safetynet.saftynetalerts.repository.PersonsRepository;
import com.safetynet.saftynetalerts.repository.readers.IDataFirestationsReader;
import com.safetynet.saftynetalerts.repository.readers.IDataMedicalRecordsReader;
import com.safetynet.saftynetalerts.repository.readers.IDataPersonsReader;
import com.safetynet.saftynetalerts.repository.readers.JSONFirestationsDataReader;
import com.safetynet.saftynetalerts.repository.readers.JSONMedicalRecordsDataReader;
import com.safetynet.saftynetalerts.repository.readers.JSONPersonsDataReader;

import jakarta.annotation.PostConstruct;

@Service
public class ObjectsCreator implements IObjectsCreator {

	@Autowired
	private PersonsRepository personsList;
	
	@Autowired
	private FirestationsRepository firestationsList;
	
	@Autowired
	private MedicalRecordsRepository medicalRecordsList;

	String filePath = "./src/main/resources/data.json"; 

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

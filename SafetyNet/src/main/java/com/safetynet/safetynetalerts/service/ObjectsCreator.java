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

	private final PersonsRepository personsRepository;

	private final FirestationsRepository firestationsRepository;

	private final MedicalRecordsRepository medicalRecordsRepository;

	@Value("${com.safetynet.saftynetalerts.path}")
	private String filePath;

	public ObjectsCreator(PersonsRepository personsList, FirestationsRepository firestationsList,
			MedicalRecordsRepository medicalRecordsList) {
		this.personsRepository = personsList;
		this.firestationsRepository = firestationsList;
		this.medicalRecordsRepository = medicalRecordsList;
	}

	/**
	 * Reads a JSON file and extracts and sets the person respository, the
	 * firestation repository and the medical record repository.
	 * 
	 * @throws IOException
	 */
	@Override
	@PostConstruct
	public void run() throws IOException {

		// Persons //
		IDataPersonsReader pReader = new JSONPersonsDataReader(filePath);
		personsRepository.setPersons(pReader.readPersons());

		// Firestations //
		IDataFirestationsReader fsReader = new JSONFirestationsDataReader(filePath);
		firestationsRepository.setFirestations(fsReader.readFirestations());

		// MedicalRecords //
		IDataMedicalRecordsReader mrReader = new JSONMedicalRecordsDataReader(filePath);
		medicalRecordsRepository.setMedicalRecords(mrReader.readMedicalRecords());
	}

}
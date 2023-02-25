package com.safetynet.saftynetalerts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynet.saftynetalerts.repository.FirestationsRepository;
import com.safetynet.saftynetalerts.repository.MedicalRecordsRepository;
import com.safetynet.saftynetalerts.repository.PersonsRepository;
import com.safetynet.saftynetalerts.repository.readers.IDataFirestationsReader;
import com.safetynet.saftynetalerts.repository.readers.IDataMedicalRecordsReader;
import com.safetynet.saftynetalerts.repository.readers.IDataPersonsReader;
import com.safetynet.saftynetalerts.repository.readers.JSONFirestationsDataReader;
import com.safetynet.saftynetalerts.repository.readers.JSONMedicalRecordsDataReader;
import com.safetynet.saftynetalerts.repository.readers.JSONPersonsDataReader;

@SpringBootApplication
public class SafetyNetApplication implements CommandLineRunner {

	@Autowired
	private PersonsRepository personsList;
	
	@Autowired
	private FirestationsRepository firestationsList;
	
	@Autowired
	private MedicalRecordsRepository medicalRecordsList;

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String filePath = "./src/main/resources/data.json"; 

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

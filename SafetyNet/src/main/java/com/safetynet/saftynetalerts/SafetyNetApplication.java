package com.safetynet.saftynetalerts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynet.saftynetalerts.repository.Firestations;
import com.safetynet.saftynetalerts.repository.IDataFirestationsReader;
import com.safetynet.saftynetalerts.repository.IDataMedicalRecordsReader;
import com.safetynet.saftynetalerts.repository.IDataPersonsReader;
import com.safetynet.saftynetalerts.repository.JSONFirestationsDataReader;
import com.safetynet.saftynetalerts.repository.JSONMedicalRecordsDataReader;
import com.safetynet.saftynetalerts.repository.JSONPersonsDataReader;
import com.safetynet.saftynetalerts.repository.MedicalRecords;
import com.safetynet.saftynetalerts.repository.Persons;

@SpringBootApplication
public class SafetyNetApplication implements CommandLineRunner {

	@Autowired
	private Persons personsList;
	
	@Autowired
	private Firestations firestationsList;
	
	@Autowired
	private MedicalRecords medicalRecordsList;

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String filePath = "./src/main/resources/data.json";

		// Persons //
		IDataPersonsReader pReader = new JSONPersonsDataReader(filePath);
		personsList.setPersons(pReader.readPersons());
		
		// Firestations 
		IDataFirestationsReader fsReader = new JSONFirestationsDataReader(filePath);
		firestationsList.setFirestations(fsReader.readFirestations());
		
		// MedicalRecords 
		IDataMedicalRecordsReader mrReader = new JSONMedicalRecordsDataReader(filePath);
		medicalRecordsList.setMedicalRecords(mrReader.readMedicalRecords());


	}

}

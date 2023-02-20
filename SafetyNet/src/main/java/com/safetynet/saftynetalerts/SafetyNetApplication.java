package com.safetynet.saftynetalerts;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.safetynet.saftynetalerts.controller.PersonsController;
import com.safetynet.saftynetalerts.model.Person;
import com.safetynet.saftynetalerts.repository.Firestations;
import com.safetynet.saftynetalerts.repository.IDataFirestationsReader;
import com.safetynet.saftynetalerts.repository.IDataMedicalRecordsReader;
import com.safetynet.saftynetalerts.repository.IDataPersonsReader;
import com.safetynet.saftynetalerts.repository.JSONFirestationsDataReader;
import com.safetynet.saftynetalerts.repository.JSONMedicalRecordsDataReader;
import com.safetynet.saftynetalerts.repository.JSONPersonsDataReader;
import com.safetynet.saftynetalerts.repository.MedicalRecords;
import com.safetynet.saftynetalerts.repository.Persons;
import com.safetynet.saftynetalerts.repository.StringToObjectsTest;

@SpringBootApplication
public class SafetyNetApplication implements CommandLineRunner {

	@Autowired
	private Persons personsList;

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String filePath = "./src/main/resources/data.json";

		// Persons //
		IDataPersonsReader pReader = new JSONPersonsDataReader(filePath);

		personsList.setPersons(pReader.readPersons());
		
		System.out.println("*** Personnes ***");
		System.out.println(personsList.getPersons().get(0).toString());
		System.out.println(personsList.getPersons().get(1).toString());


	}

}

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
import com.safetynet.saftynetalerts.controller.Controller;
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

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String filePath = "./src/main/resources/data.json";
//
////		StringToObjectsTest.StringToObject("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\","
////				+ " \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }");
////
////	
////		StringToObjectsTest.ArrayStringToObjectsList("[{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" },"
////				+ "{ \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6513\", \"email\":\"drk@email.com\" },"
////				+ "{ \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"tenz@email.com\" }]");
////		
////		StringToObjectsTest.JsonTreeToObjectsList("./src/main/resources/data.json");
//
////	IDataReader reader = new JSONDataReader("./src/main/resources/data.json");
////	reader.readDataSource();
//
//		// Test1 //
////	Persons persons = new Persons();
////	persons.createList(filePath);
////	
////	System.out.println(persons.getPersons().get(22).toString());
////	System.out.println(persons.getPersons().get(3).toString());
//
		// Test2 //

		// Persons //
		IDataPersonsReader pReader = new JSONPersonsDataReader(filePath);

		Persons personsList = new Persons();
		personsList.setPersons(pReader.readPersons());
		
		System.out.println("*** Personnes ***");
		System.out.println(personsList.getPersons().get(0).toString());
		System.out.println(personsList.getPersons().get(1).toString());

//		// Medicals Records //
//
//		IDataMedicalRecordsReader mrReader = new JSONMedicalRecordsDataReader(filePath);
//
//		MedicalRecords medicalRecordsList = new MedicalRecords();
//		medicalRecordsList.setMedicalRecords(mrReader.readMedicalRecords());
//
//		System.out.println("*** Medical Records ***");
//
//		System.out.println(medicalRecordsList.getMedicalRecords().get(0).toString());
//		System.out.println("-- Medications --");
//		System.out.println(medicalRecordsList.getMedicalRecords().get(0).getMedications().get(0).toString());
//		System.out.println(medicalRecordsList.getMedicalRecords().get(0).getMedications().get(1).toString());
//		System.out.println("-- Allergies --");
//		System.out.println(medicalRecordsList.getMedicalRecords().get(0).getAllergies().get(0).toString());
//
//		System.out.println(medicalRecordsList.getMedicalRecords().get(1).toString());
//
//		// Firestations //
//		IDataFirestationsReader fsReader = new JSONFirestationsDataReader(filePath);
//
//		Firestations firestationsList = new Firestations();
//		firestationsList.setFirestations(fsReader.readFirestations());
//
//		System.out.println("*** Firestations ***");
//		System.out.println(firestationsList.getFirestations().get(0).toString());
//		System.out.println(firestationsList.getFirestations().get(2).toString());

	}

}

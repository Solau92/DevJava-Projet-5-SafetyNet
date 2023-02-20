package com.safetynet.saftynetalerts.repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.model.Person;

public class JSONMedicalRecordsDataReader implements IDataMedicalRecordsReader {

	String filePath;
	
	public JSONMedicalRecordsDataReader(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public List<MedicalRecord> readMedicalRecords() throws IOException {
		File file = new File(filePath);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode medicalRecordsNode = objectMapper.readTree(file).get("medicalrecords");

		ObjectMapper objectMapper2 = new ObjectMapper();
		List<MedicalRecord> listMedicalRecords = objectMapper2.readValue(medicalRecordsNode.toString(), new TypeReference<List<MedicalRecord>>() {});
		
		return listMedicalRecords;
	}

}

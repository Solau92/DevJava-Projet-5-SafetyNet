package com.safetynet.saftynetalerts.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.service.IMedicalRecordService;

@RestController
public class MedicalRecordsController {

	private final IMedicalRecordService medicalRecordsService;
	
	public MedicalRecordsController(IMedicalRecordService medicalRecordsService) {
		this.medicalRecordsService = medicalRecordsService;
	}

	@GetMapping("/medicalRecords")
	public ResponseEntity<List<MedicalRecord>> getMedicalRecords() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(medicalRecordsService.getAllMedicalRecords());
	}

	@PostMapping("/medicalRecord")
	public ResponseEntity<String> createMedicalRecord(@RequestBody MedicalRecord medicalRecord)
			throws StreamWriteException, DatabindException, IOException {
		if (Objects.isNull(medicalRecord)) {
			return ResponseEntity.noContent().build();
		}
		MedicalRecord medicalRecordCreated = medicalRecordsService.saveMedicalRecord(medicalRecord);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/medicalRecord")
	public ResponseEntity<String> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord)
			throws StreamWriteException, DatabindException, IOException {
		if (Objects.isNull(medicalRecord)) {
			return ResponseEntity.noContent().build();
		}
		MedicalRecord medicalRecordUpdated = medicalRecordsService.updateMedicalRecord(medicalRecord);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	@DeleteMapping("/medicalRecord")
	public ResponseEntity<String> deleteMedicalRecord(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lalstName) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();

	}
}

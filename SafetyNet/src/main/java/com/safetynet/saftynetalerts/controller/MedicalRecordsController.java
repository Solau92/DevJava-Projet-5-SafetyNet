package com.safetynet.saftynetalerts.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.saftynetalerts.exception.MedicalRecordAlreadyExistsException;
import com.safetynet.saftynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.service.IMedicalRecordService;

@RestController
public class MedicalRecordsController {

	private final IMedicalRecordService medicalRecordsService;
	
	public MedicalRecordsController(IMedicalRecordService medicalRecordsService) {
		this.medicalRecordsService = medicalRecordsService;
	}

	@GetMapping("/medicalRecords")
	public ResponseEntity<List<MedicalRecord>> getMedicalRecords() throws MedicalRecordNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(medicalRecordsService.getAllMedicalRecords());
	}

	@PostMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws MedicalRecordAlreadyExistsException {
		return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecordsService.saveMedicalRecord(medicalRecord));
	}

	@PutMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws MedicalRecordNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(medicalRecordsService.updateMedicalRecord(medicalRecord));
	}

	@DeleteMapping("/medicalRecord")
	public ResponseEntity<String> deleteMedicalRecord(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) throws MedicalRecordNotFoundException {
		medicalRecordsService.deleteMedicalRecord(firstName, lastName);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();

	}
}

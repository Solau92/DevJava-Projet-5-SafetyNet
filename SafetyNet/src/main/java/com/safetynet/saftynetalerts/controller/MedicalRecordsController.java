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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MedicalRecordsController {

	private final IMedicalRecordService medicalRecordsService;
	
	public MedicalRecordsController(IMedicalRecordService medicalRecordsService) {
		this.medicalRecordsService = medicalRecordsService;
	}

	@GetMapping("/medicalRecords")
	public ResponseEntity<List<MedicalRecord>> getAllMedicalRecords() throws MedicalRecordNotFoundException {
		log.info("Request : get all MedicalRecords");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(medicalRecordsService.getAllMedicalRecords());
	}

	@PostMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws MedicalRecordAlreadyExistsException {
		log.info("Request : save medical record for person named {} {}", medicalRecord.getFirstName(), medicalRecord.getLastName());
		return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecordsService.saveMedicalRecord(medicalRecord));
	}

	@PutMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws MedicalRecordNotFoundException {
		log.info("Request : update medical record for person named {} {}", medicalRecord.getFirstName(), medicalRecord.getLastName());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(medicalRecordsService.updateMedicalRecord(medicalRecord));
	}

	@DeleteMapping("/medicalRecord")
	public ResponseEntity<Void> deleteMedicalRecord(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) throws MedicalRecordNotFoundException {
		log.info("Request : delete medical record for person named {} {}", firstName, lastName);
		medicalRecordsService.deleteMedicalRecord(firstName, lastName);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();

	}
}

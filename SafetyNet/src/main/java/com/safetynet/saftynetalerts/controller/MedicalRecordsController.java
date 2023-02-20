package com.safetynet.saftynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.service.MedicalRecordService;

@RestController
public class MedicalRecordsController {
	
	@Autowired
	private MedicalRecordService medicalRecordsService;
	
	@GetMapping("/medicalRecords")
	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecordsService.getAllMedicalRecords();
	}

}

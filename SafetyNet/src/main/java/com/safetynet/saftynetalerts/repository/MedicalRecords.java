package com.safetynet.saftynetalerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.model.Person;

import lombok.Data;

@Component
@Data
public class MedicalRecords {
	
	private List<MedicalRecord> medicalRecords;

}

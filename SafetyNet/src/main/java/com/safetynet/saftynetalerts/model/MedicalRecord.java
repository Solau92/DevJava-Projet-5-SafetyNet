package com.safetynet.saftynetalerts.model;

import java.time.LocalDate;
import java.util.List;

public class MedicalRecord {

	private String firstName;
	private String lastName;
	private LocalDate birthdate;
	private List<String> medications;
	private List<String> allergies;
}

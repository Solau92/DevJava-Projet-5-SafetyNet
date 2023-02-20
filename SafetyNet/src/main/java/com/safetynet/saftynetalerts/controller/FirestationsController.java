package com.safetynet.saftynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.saftynetalerts.model.Firestation;
import com.safetynet.saftynetalerts.service.FirestationService;

@RestController
public class FirestationsController {
	
	@Autowired
	private FirestationService firestationService;
	
	@GetMapping("/firestations")
	public List<Firestation> getFirestations() {
		return firestationService.getAllFirestations();
	}

}

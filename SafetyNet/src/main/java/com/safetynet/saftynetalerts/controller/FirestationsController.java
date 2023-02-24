package com.safetynet.saftynetalerts.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.saftynetalerts.model.Firestation;
import com.safetynet.saftynetalerts.service.IFirestationService;

@RestController
public class FirestationsController {
	
	@Autowired
	private IFirestationService firestationService;
	
	@GetMapping("/firestations")
	public ResponseEntity<List<Firestation>> getFirestations() {	
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(firestationService.getAllFirestations());	
	}
	
	@PostMapping("/firestation")
	public ResponseEntity<String> createFirestation(@RequestBody Firestation firestation) {
		if(Objects.isNull(firestation)) {
			return ResponseEntity.noContent().build();
		}
		Firestation firestationCreated = firestationService.saveFirestation(firestation);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/firestation")
	public ResponseEntity<String> updateFirestation(@RequestBody Firestation firestation) {
		if(Objects.isNull(firestation)) {
			return ResponseEntity.noContent().build();
		}
		Firestation firestationUpdated = firestationService.updateFirestation(firestation);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@DeleteMapping("/firestation")
	public ResponseEntity<String> deleteFirestationByAddress(@RequestParam("address") String address) {
		// Si pas trouvé 
		// Else (trouvé) : 
		firestationService.deleteFirestationByAddress(address);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();	
	}
	
//	@DeleteMapping("/firestation")
//	public ResponseEntity<String> deleteFirestationById(@RequestParam("station") int idStation) {
//		// Si pas trouvé 
//		// Else (trouvé) : 
//		firestationService.deleteFirestationById(idStation);
//		return ResponseEntity.status(HttpStatus.ACCEPTED).build();	
//	}
}

package com.safetynet.saftynetalerts.controller;

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

import com.safetynet.saftynetalerts.model.FirestationSpot;
import com.safetynet.saftynetalerts.service.IFirestationService;

@RestController
public class FirestationsController {
	
	private final IFirestationService firestationService;
	
	public FirestationsController(IFirestationService firestationService) {
		this.firestationService = firestationService;
	}
	
	@GetMapping("/firestations")
	public ResponseEntity<List<FirestationSpot>> getFirestations() {	
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(firestationService.getAllFirestations());	
	}
	
	@PostMapping("/firestation")
	public ResponseEntity<String> createFirestation(@RequestBody FirestationSpot firestation) {
		if(Objects.isNull(firestation)) {
			return ResponseEntity.noContent().build();
		}
		FirestationSpot firestationCreated = firestationService.saveFirestation(firestation);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/firestation")
	public ResponseEntity<String> updateFirestation(@RequestBody FirestationSpot firestation) {
		if(Objects.isNull(firestation)) {
			return ResponseEntity.noContent().build();
		}
		FirestationSpot firestationUpdated = firestationService.updateFirestation(firestation);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@DeleteMapping("/firestation")
	public ResponseEntity<String> deleteFirestationByAddress(@RequestParam("address") String address) {
		// Si pas trouvé 
		// Else (trouvé) : 
		firestationService.deleteFirestationByAddress(address);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();	
	}
	
	@DeleteMapping("/firestation/byId")
	public ResponseEntity<String> deleteFirestationById(@RequestParam("stationId") int stationId) {
		// Si pas trouvé 
		// Else (trouvé) : 
		firestationService.deleteFirestationById(stationId);
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

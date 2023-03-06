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

import com.safetynet.saftynetalerts.exception.FirestationAlreadyExistsException;
import com.safetynet.saftynetalerts.exception.FirestationNotFoundException;
import com.safetynet.saftynetalerts.model.FirestationSpot;
import com.safetynet.saftynetalerts.service.IFirestationService;

@RestController
public class FirestationsController {
	
	private final IFirestationService firestationService;
	
	public FirestationsController(IFirestationService firestationService) {
		this.firestationService = firestationService;
	}
	
	@GetMapping("/firestations")
	public ResponseEntity<List<FirestationSpot>> getFirestations() throws FirestationNotFoundException {	
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(firestationService.getAllFirestations());	
	}
	
	@PostMapping("/firestation")
	public ResponseEntity<String> createFirestation(@RequestBody FirestationSpot firestation) throws FirestationAlreadyExistsException {
		firestationService.saveFirestation(firestation);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/firestation")
	public ResponseEntity<String> updateFirestation(@RequestBody FirestationSpot firestation) throws FirestationNotFoundException {
		firestationService.updateFirestation(firestation);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@DeleteMapping("/firestation")
	public ResponseEntity<String> deleteFirestationByAddress(@RequestParam("address") String address) throws FirestationNotFoundException {
		firestationService.deleteFirestationByAddress(address);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();	
	}
	
	// TODO : voir si je garde
	@DeleteMapping("/firestation/byId")
	public ResponseEntity<String> deleteFirestationById(@RequestParam("stationId") int stationId) { 
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
	
	@GetMapping("/firestationById")
	public ResponseEntity<List<String>> getFirestationsAddressesById(@RequestParam("stationId") int stationId) throws FirestationNotFoundException {	
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(firestationService.getAddressesWithId(stationId));	
	}
	
	@GetMapping("/firestationByAddress")
	public ResponseEntity<Integer> getFirestationsIdByAddress(@RequestParam("address") String address) throws FirestationNotFoundException {	
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(firestationService.getIdWithAddress(address));	
	}
	
}

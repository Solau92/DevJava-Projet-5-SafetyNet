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
import com.safetynet.saftynetalerts.model.Firestation;
import com.safetynet.saftynetalerts.service.IFirestationService;

@RestController
public class FirestationsController {
	
	private final IFirestationService firestationService;
	
	public FirestationsController(IFirestationService firestationService) {
		this.firestationService = firestationService;
	}
	
	@GetMapping("/firestations")
	public ResponseEntity<List<Firestation>> getFirestations() throws FirestationNotFoundException {	
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(firestationService.getAllFirestations());	
	}
	
	@PostMapping("/firestation")
	public ResponseEntity<Firestation> createFirestation(@RequestBody Firestation firestation) throws FirestationAlreadyExistsException {
		return ResponseEntity.status(HttpStatus.CREATED).body(firestationService.saveFirestation(firestation));
	}
	
	@PutMapping("/firestation")
	public ResponseEntity<Firestation> updateFirestation(@RequestBody Firestation firestation) throws FirestationNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(firestationService.updateFirestation(firestation));
	}
	
	@DeleteMapping("/firestation")
	public ResponseEntity<String> deleteFirestationByAddress(@RequestParam("address") String address) throws FirestationNotFoundException {
		firestationService.deleteFirestationByAddress(address);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();	
	}
	
//	// TODO : voir si je garde
//	@DeleteMapping("/firestation/byId")
//	public ResponseEntity<String> deleteFirestationById(@RequestParam("stationId") int stationId) { 
//		firestationService.deleteFirestationById(stationId);
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

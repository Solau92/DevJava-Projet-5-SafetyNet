package com.safetynet.safetynetalerts.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.DTOChildAlert;
import com.safetynet.safetynetalerts.model.DTOFire;
import com.safetynet.safetynetalerts.model.DTOFirestation;
import com.safetynet.safetynetalerts.model.DTOFlood;
import com.safetynet.safetynetalerts.model.DTOPersonInfo;
import com.safetynet.safetynetalerts.service.IURLChildAlertService;
import com.safetynet.safetynetalerts.service.IURLFireService;
import com.safetynet.safetynetalerts.service.IURLFirestationService;
import com.safetynet.safetynetalerts.service.IURLFloodService;
import com.safetynet.safetynetalerts.service.IURLPersonInfoService;
import com.safetynet.safetynetalerts.service.IURLPhoneAlertService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class URLController {

	private final IURLPhoneAlertService uRLPhoneAlertService;

	private final IURLFirestationService uRLFirestationService;

	private final IURLChildAlertService uRLChildAlertService;

	private final IURLFireService uRLFireService;

	private final IURLFloodService uRLFloodService;

	private final IURLPersonInfoService uRLPersonInfoService;

	public URLController(IURLPhoneAlertService uRLPhoneAlertService, IURLFirestationService uRLFirestationService,
			IURLChildAlertService uRLChildAlertService, IURLFireService uRLFireService,
			IURLPersonInfoService uRLPersonInfoService, IURLFloodService uRLFloodService) {
		this.uRLPhoneAlertService = uRLPhoneAlertService;
		this.uRLFirestationService = uRLFirestationService;
		this.uRLChildAlertService = uRLChildAlertService;
		this.uRLFireService = uRLFireService;
		this.uRLFloodService = uRLFloodService;
		this.uRLPersonInfoService = uRLPersonInfoService;
	}
	
	@GetMapping("/firestation")
	public ResponseEntity<DTOFirestation> getFirestation(@RequestParam("stationNumber") int stationId)
			throws PersonNotFoundException, MedicalRecordNotFoundException, FirestationNotFoundException {
		log.info("Request : return a list of Persons living in the area covered by station id : {}", stationId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uRLFirestationService.getFirestation(stationId));
	}

	@GetMapping("/childAlert")
	public ResponseEntity<List<DTOChildAlert>> getChildAlert(@RequestParam("address") String address)
			throws PersonNotFoundException, MedicalRecordNotFoundException {
		log.info("Request : return a list of children living at this address : {}, and their family members", address);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uRLChildAlertService.getChildAlert(address));
	}

	@GetMapping("/phoneAlert")
	public ResponseEntity<Set<String>> getPhoneAlert(@RequestParam("firestation") int stationId)
			throws PersonNotFoundException, FirestationNotFoundException {
		log.info("Request : return a list of phone numbers of inhabitants in the area of a the firestation number {}", stationId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uRLPhoneAlertService.getPhoneAlert(stationId));
	}

	@GetMapping("/fire")
	public ResponseEntity<DTOFire> getFire(@RequestParam("address") String address) throws PersonNotFoundException,
			MedicalRecordNotFoundException, FirestationNotFoundException {
		log.info("Request : return a list of inhabitants at this address : {}, and the id of the firestation corresponding", address);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uRLFireService.getFire(address));
	}

	@GetMapping("/flood/stations")
	public ResponseEntity<List<DTOFlood>> getFlood(@RequestParam("stations") List<Integer> stationIdList)
			throws PersonNotFoundException, MedicalRecordNotFoundException,
			FirestationNotFoundException {
		log.info("Request : return a list of families in the area of the firestation with the id : {}", stationIdList);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uRLFloodService.getFlood(stationIdList));
	}

	@GetMapping("/personInfo")
	public ResponseEntity<List<DTOPersonInfo>> getPersonInfo(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) throws PersonNotFoundException,
			MedicalRecordNotFoundException {
		log.info("Request : return informations about the person named {} {}", firstName, lastName);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uRLPersonInfoService.getPersonInfo(firstName, lastName));
	}

}

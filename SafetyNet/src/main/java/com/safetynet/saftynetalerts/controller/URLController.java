package com.safetynet.saftynetalerts.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.saftynetalerts.exception.FirestationNotFoundException;
import com.safetynet.saftynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.saftynetalerts.exception.MoreThanOneMedicalRecordFoundException;
import com.safetynet.saftynetalerts.exception.MoreThanOnePersonFoundException;
import com.safetynet.saftynetalerts.exception.PersonNotFoundException;
import com.safetynet.saftynetalerts.model.DTOChildAlert;
import com.safetynet.saftynetalerts.model.DTOFire;
import com.safetynet.saftynetalerts.model.DTOFirestation;
import com.safetynet.saftynetalerts.model.DTOFirestationPerson;
import com.safetynet.saftynetalerts.model.DTOFlood;
import com.safetynet.saftynetalerts.model.DTOPersonInfo;
import com.safetynet.saftynetalerts.service.IURLChildAlertService;
import com.safetynet.saftynetalerts.service.IURLFireService;
import com.safetynet.saftynetalerts.service.IURLFirestationService;
import com.safetynet.saftynetalerts.service.IURLFloodService;
import com.safetynet.saftynetalerts.service.IURLPersonInfoService;
import com.safetynet.saftynetalerts.service.IURLPhoneAlertService;

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
		ResponseEntity<DTOFirestation> response = ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(uRLFirestationService.getFirestation(stationId));
		List<DTOFirestationPerson> list = response.getBody().getFirestationPersons();
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(uRLFirestationService.getFirestation(stationId));

		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uRLFirestationService.getFirestation(stationId));
	}

	@GetMapping("/childAlert")
	public ResponseEntity<List<DTOChildAlert>> getChildAlert(@RequestParam("address") String address)
			throws PersonNotFoundException, MedicalRecordNotFoundException, MoreThanOneMedicalRecordFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uRLChildAlertService.getChildAlert(address));
	}

	@GetMapping("/phoneAlert")
	public ResponseEntity<Set<String>> getPhoneAlert(@RequestParam("firestation") int stationId)
			throws PersonNotFoundException, FirestationNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uRLPhoneAlertService.getPhoneAlert(stationId));
	}

	@GetMapping("/fire")
	public ResponseEntity<DTOFire> getFire(@RequestParam("address") String address) throws PersonNotFoundException,
			MedicalRecordNotFoundException, MoreThanOneMedicalRecordFoundException, FirestationNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uRLFireService.getFire(address));
	}

	@GetMapping("/flood/stations")
	public ResponseEntity<List<DTOFlood>> getFlood(@RequestParam("stations") List<Integer> stationIdList)
			throws PersonNotFoundException, MedicalRecordNotFoundException, MoreThanOneMedicalRecordFoundException,
			FirestationNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uRLFloodService.getFlood(stationIdList));
	}

	@GetMapping("/personInfo")
	public ResponseEntity<List<DTOPersonInfo>> getPersonInfo(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) throws PersonNotFoundException, MoreThanOnePersonFoundException,
			MedicalRecordNotFoundException, MoreThanOneMedicalRecordFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uRLPersonInfoService.getPersonInfo(firstName, lastName));
	}

}

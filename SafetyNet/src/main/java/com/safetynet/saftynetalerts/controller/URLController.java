package com.safetynet.saftynetalerts.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	private final IURLPhoneAlertService URLPhoneAlertService;

	private final IURLFirestationService URLFirestationService;

	private final IURLChildAlertService URLChildAlertService;

	private final IURLFireService URLFireService;

	private final IURLPersonInfoService URLPersonInfoService;

	private final IURLFloodService URLFloodService;	

	public URLController(IURLPhoneAlertService URLPhoneAlertService, IURLFirestationService URLFirestationService,
			IURLChildAlertService URLChildAlertService, IURLFireService URLFireService,
			IURLPersonInfoService URLPersonInfoService, IURLFloodService URLFloodService) {
		this.URLPhoneAlertService = URLPhoneAlertService;
		this.URLFirestationService = URLFirestationService;
		this.URLChildAlertService = URLChildAlertService;
		this.URLFireService = URLFireService;
		this.URLPersonInfoService = URLPersonInfoService;
		this.URLFloodService = URLFloodService;
	}

	@GetMapping("/firestation")
	public ResponseEntity<DTOFirestation> getFirestation(@RequestParam("stationNumber") int stationId) throws PersonNotFoundException {
		ResponseEntity<DTOFirestation> response = ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(URLFirestationService.getFirestation(stationId));
		List<DTOFirestationPerson> list = response.getBody().getFirestationPersons();
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(URLFirestationService.getFirestation(stationId));

		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(URLFirestationService.getFirestation(stationId));
	}

	@GetMapping("/childAlert")
	public ResponseEntity<List<DTOChildAlert>> getChildAlert(@RequestParam("address") String address) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(URLChildAlertService.getChildAlert(address));
	}

	@GetMapping("/phoneAlert")
	public ResponseEntity<List<String>> getPhoneAlert(@RequestParam("firestation") int stationId) throws PersonNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(URLPhoneAlertService.getPhoneAlert(stationId));
	}

	@GetMapping("/fire")
	public ResponseEntity<DTOFire> getFire(@RequestParam("address") String address) throws PersonNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(URLFireService.getFire(address));
	}

	@GetMapping("/personInfo")
	public ResponseEntity<List<DTOPersonInfo>> getPersonInfo(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) throws PersonNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(URLPersonInfoService.getPersonInfo(firstName, lastName));
	}

	@GetMapping("/flood/stations")
	public ResponseEntity<List<DTOFlood>> getFlood(@RequestParam("stations") List<Integer> stationIdList) throws PersonNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(URLFloodService.getFlood(stationIdList));
	}
	
}

package com.safetynet.saftynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.saftynetalerts.model.DTOChildAlert;
import com.safetynet.saftynetalerts.model.DTOFire;
import com.safetynet.saftynetalerts.model.DTOFirestation;
import com.safetynet.saftynetalerts.model.DTOPersonInfo;
import com.safetynet.saftynetalerts.service.IURLChildAlertService;
import com.safetynet.saftynetalerts.service.IURLFireService;
import com.safetynet.saftynetalerts.service.IURLFirestationService;
import com.safetynet.saftynetalerts.service.IURLPersonInfoService;
import com.safetynet.saftynetalerts.service.IURLPhoneAlertService;
import com.safetynet.saftynetalerts.service.URLFireService;
import com.safetynet.saftynetalerts.service.URLPersonInfoService;

@RestController
public class URLController {
	
	@Autowired
	private IURLPhoneAlertService URLPhoneAlertService;
	
	@Autowired
	private IURLFirestationService URLFirestationService;
	
	@Autowired
	private IURLChildAlertService URLChildAlertService;
	
	@Autowired
	private IURLFireService URLFireService;
	
	@Autowired
	private IURLPersonInfoService URLPersonInfoService;
	
	
	@GetMapping("/firestation")
	public ResponseEntity<DTOFirestation> getFirestation(@RequestParam("stationNumber") int stationId) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(URLFirestationService.getFirestation(stationId));
	}
	
	@GetMapping("/childAlert")
	public ResponseEntity<List<DTOChildAlert>> getChildAlert(@RequestParam("address") String address) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(URLChildAlertService.getChildAlert(address));
	}
	
	@GetMapping("/phoneAlert")
	public ResponseEntity<List<String>> getPhoneAlert(@RequestParam("firestation") int stationId) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(URLPhoneAlertService.getPhoneAlert(stationId));
	}
	
	@GetMapping("/fire")
	public ResponseEntity<DTOFire> getFire(@RequestParam("address") String address) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(URLFireService.getFire(address));
	}
	
	@GetMapping("/personInfo")
	public ResponseEntity<List<DTOPersonInfo>> getPersonInfo(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(URLPersonInfoService.getPersonInfo(firstName, lastName));
	}
	
 
}

package com.safetynet.saftynetalerts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TotoController {
	
	
//	  private final TotoService totoService;
//	  
//	  public TotoController(TotoService totoService) { this.totoService=
//	  totoService; }
	 
	
	@GetMapping("/toto")
	public ResponseEntity<String> getToto() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("hello Toto");
	}

}

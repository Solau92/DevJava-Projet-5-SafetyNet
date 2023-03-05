package com.safetynet.saftynetalerts.controller;

import java.io.IOException;
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
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.safetynet.saftynetalerts.exception.NotFoundException;
import com.safetynet.saftynetalerts.model.Person;
import com.safetynet.saftynetalerts.service.IPersonService;

@RestController
public class PersonsController {

	private final IPersonService personService;
	
	public PersonsController(IPersonService personService) {
		this.personService = personService;
	}
	
	@GetMapping("/persons")
	public ResponseEntity<List<Person>> getPersons() throws NotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(personService.getAllPersons());
	}

	@PostMapping("/person")
	public ResponseEntity<String> createPerson(@RequestBody Person person) throws StreamWriteException, DatabindException, IOException {
		if (Objects.isNull(person)) {
//			return ResponseEntity.noContent().build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Person personCreated = personService.savePerson(person);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	// Essai exception 
	@PutMapping("/person")
	public ResponseEntity<String> updatePerson(@RequestBody Person person) throws NotFoundException {
		Person personUpdated = personService.updatePerson(person);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
//	@PutMapping("/person")
//	public ResponseEntity<String> updatePerson(@RequestBody Person person) throws NotFoundException {
//		if (Objects.isNull(person)) {
//			return ResponseEntity.noContent().build();
//		}
//		Person personUpdated = personService.updatePerson(person);
//		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
//	}
 
	@DeleteMapping("/person")
	public ResponseEntity<String> deletePerson(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		// Si pas trouvé
		// Else (trouvé) :
		personService.deletePerson(firstName, lastName);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	// Essai exception 
	@GetMapping("/person")
	public ResponseEntity<List<Person>> getPersonsByLastName(@RequestParam("lastName") String lastName) throws NotFoundException {			
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(personService.getPersonsByLastName(lastName));		
	}	
	
	
	@GetMapping("/personByAddress")
	public List<Person> getPersonsByAddress(@RequestParam("address") String address) {
		return personService.getPersonsByAddress(address);
	}


	@GetMapping("/communityEmail")
	public List<String> getCommunityEmail(@RequestParam("city") String city) {
		return personService.getCommunityEmail(city);
	}

}

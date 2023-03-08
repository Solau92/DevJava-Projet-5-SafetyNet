package com.safetynet.saftynetalerts.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.safetynet.saftynetalerts.exception.PersonAlreadyExistsException;
import com.safetynet.saftynetalerts.exception.PersonNotFoundException;
import com.safetynet.saftynetalerts.model.Person;
import com.safetynet.saftynetalerts.service.IPersonService;

@RestController
public class PersonsController {

	private final IPersonService personService;
	
	public PersonsController(IPersonService personService) {
		this.personService = personService;
	}
	
	@GetMapping("/persons")
	public ResponseEntity<List<Person>> getPersons() throws PersonNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(personService.getAllPersons());
	}

	@PostMapping("/person")
	public ResponseEntity<Person> createPerson(@RequestBody Person person) throws PersonAlreadyExistsException {
		return ResponseEntity.status(HttpStatus.CREATED).body(personService.savePerson(person));
	}

	@PutMapping("/person")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person) throws PersonNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(personService.updatePerson(person));
	}
 
	@DeleteMapping("/person")
	public ResponseEntity<String> deletePerson(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) throws PersonNotFoundException {
		personService.deletePerson(firstName, lastName);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@GetMapping("/person")
	public ResponseEntity<List<Person>> getPersonsByLastName(@RequestParam("lastName") String lastName) throws PersonNotFoundException {			
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(personService.getPersonsByLastName(lastName));		
	}		
	
	@GetMapping("/personByAddress")
	public ResponseEntity<List<Person>> getPersonsByAddress(@RequestParam("address") String address) throws PersonNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(personService.getPersonsByAddress(address));
	}

	@GetMapping("/communityEmail")
	public ResponseEntity<Set<String>> getCommunityEmail(@RequestParam("city") String city) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(personService.getCommunityEmail(city));
	}

}

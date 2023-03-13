package com.safetynet.safetynetalerts.controller;

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

import com.safetynet.safetynetalerts.exception.PersonAlreadyExistsException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.IPersonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PersonsController {

	private final IPersonService personService;
	
	public PersonsController(IPersonService personService) {
		this.personService = personService;
	}
	
	@GetMapping("/persons")
	public ResponseEntity<List<Person>> getPersons() throws PersonNotFoundException {
		log.info("Request : get all Persons");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(personService.getAllPersons());
	}

	@PostMapping("/person")
	public ResponseEntity<Person> createPerson(@RequestBody Person person) throws PersonAlreadyExistsException {
		log.info("Request : save person named {} {}", person.getFirstName(), person.getLastName());
		return ResponseEntity.status(HttpStatus.CREATED).body(personService.savePerson(person));
	}

	@PutMapping("/person")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person) throws PersonNotFoundException {
		log.info("Request : update person named {} {}", person.getFirstName(), person.getLastName());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(personService.updatePerson(person));
	}
 
	@DeleteMapping("/person")
	public ResponseEntity<Void> deletePerson(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) throws PersonNotFoundException {
		log.info("Request : delete person named {} {}", firstName, lastName);
		personService.deletePerson(firstName, lastName);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@GetMapping("/person")
	public ResponseEntity<List<Person>> getPersonsByLastName(@RequestParam("lastName") String lastName) throws PersonNotFoundException {			
		log.info("Request : get Person by name : {}", lastName);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(personService.getPersonsByLastName(lastName));		
	}		
	
	@GetMapping("/personByAddress")
	public ResponseEntity<List<Person>> getPersonsByAddress(@RequestParam("address") String address) throws PersonNotFoundException {
		log.info("Request : get Person by address : {}", address);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(personService.getPersonsByAddress(address));
	}

	@GetMapping("/communityEmail")
	public ResponseEntity<Set<String>> getCommunityEmail(@RequestParam("city") String city) {
		log.info("Request : get emails of people from this city : {}", city);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(personService.getCommunityEmail(city));
	}

}

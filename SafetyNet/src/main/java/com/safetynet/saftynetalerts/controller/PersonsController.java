package com.safetynet.saftynetalerts.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.safetynet.saftynetalerts.exception.NotFoundException;
import com.safetynet.saftynetalerts.model.Person;
import com.safetynet.saftynetalerts.repository.PersonsRepository;
import com.safetynet.saftynetalerts.service.IPersonService;
import com.safetynet.saftynetalerts.service.PersonService;

import ch.qos.logback.classic.Logger;

@RestController
public class PersonsController {

	@Autowired
	private IPersonService personService;

//	public PersonsController(PersonsRepository persons) {
//	}

//	@GetMapping("/persons")
//	public List<Person> getPersons() {
//		return personService.getAllPersons();
//	}

	/////////////////////////////
	// CRUD 
	/////////////////////////////
	
	@GetMapping("/persons")
	public ResponseEntity<List<Person>> getPersons() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(personService.getAllPersons());
	}

	@PostMapping("/person")
	public ResponseEntity<String> createPerson(@RequestBody Person person)
			throws StreamWriteException, DatabindException, IOException {
		if (Objects.isNull(person)) {
//			return ResponseEntity.noContent().build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Person personCreated = personService.savePerson(person);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/person")
	public ResponseEntity<String> updatePerson(@RequestBody Person person) {
		if (Objects.isNull(person)) {
			return ResponseEntity.noContent().build();
		}
		Person personUpdated = personService.updatePerson(person);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

//	@DeleteMapping("/person")
//	public void deletePerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
//		personService.deletePerson(firstName, lastName);
//	}
 
	@DeleteMapping("/person")
	public ResponseEntity<String> deletePerson(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		// Si pas trouvé
		// Else (trouvé) :
		personService.deletePerson(firstName, lastName);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	/////////////////////////////
	// URL 
	/////////////////////////////
	
	@GetMapping("/person")
	public ResponseEntity<List<Person>> getPersonsByLastName(@RequestParam("lastName") String lastName) throws NotFoundException {			
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(personService.getPersonsByLastName(lastName));		
	}	
	
//	public ResponseEntity<List<Person>> getPersonsByLastName(@RequestParam("lastName") String lastName) throws NotFoundException {	
//		ResponseEntity<List<Person>> response = ResponseEntity.status(HttpStatus.ACCEPTED).body(personService.getPersonsByLastName(lastName));
//		if ((response.getBody()).isEmpty() ) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(personService.getPersonsByLastName(lastName));
//		}
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body(personService.getPersonsByLastName(lastName));
//	}

	// ??????????? refaire avec responseentity ????????????????????
//	public List<Person> getPersonsByName(@RequestParam("name") String name) {
//		return personService.getPersonsByName(name);
//	}
	
	@GetMapping("/personByAddress")
	public List<Person> getPersonsByAddress(@RequestParam("address") String address) {
		return personService.getPersonsByAddress(address);
	}

//	@GetMapping("/personInfo")
//	public List<Person> getPersonsByFirstNameAndLastName(@RequestParam("firstName") String firstName,
//			@RequestParam("lastName") String lastName) {
//		return personService.getPersonsByFirstNameAndLastName(firstName, lastName);
//	}

	@GetMapping("/communityEmail")
	public List<String> getCommunityEmail(@RequestParam("city") String city) {
		return personService.getCommunityEmail(city);
	}

//	@GetMapping("/fire") // Rajouter numéro caserne
//	public List<Person> getInhabitants(@RequestParam("address") String address) {
//		return personService.getPersonsByAddress(address);
//	}
}

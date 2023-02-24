package com.safetynet.saftynetalerts.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.safetynet.saftynetalerts.model.Person;
import com.safetynet.saftynetalerts.repository.Persons;
import com.safetynet.saftynetalerts.service.PersonService;

import ch.qos.logback.classic.Logger;

@RestController
public class PersonsController {

	@Autowired
	private PersonService personService;

	public PersonsController(Persons persons) {
	}

//	@GetMapping("/persons")
//	public ResponseEntity<String> getPersons(){
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Liste des personnes :" );
//		}

	@GetMapping("/persons")
	public List<Person> getPersons() {
		return personService.getAllPersons();
	}

	@GetMapping("/person")
	public List<Person> getPersonsByName(@RequestParam("name") String name) {
		return personService.getPersonsByName(name);
	}
	
	@GetMapping("/personInfo")
	public List<Person> getPersonsByFirstNameAndLastName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
		return personService.getPersonsByFirstNameAndLastName(firstName, lastName);
	}
	
	@GetMapping("/communityEmail")
	public List<String> getCommunityEmail(@RequestParam("city") String city) {
		return personService.getCommunityEmail(city);
	}
	
	@GetMapping("/fire") // Rajouter numéro caserne 
	public List<Person> getInhabitants(@RequestParam("address") String address) {
		return personService.getInhabitants(address);
	}
	
	// Fonctionne, mais paramètres de person dans le code...s
//	@PostMapping("/person")
//	public Person createPerson(@RequestBody Person person) {
//		return personService.savePerson(person);
//	}
	
//	@PostMapping("/person")
//	public Person createPerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("address") String address) throws StreamWriteException, DatabindException, IOException {
//		Person savePerson = new Person();
//		savePerson.setFirstName(firstName);
//		savePerson.setLastName(lastName);
//		savePerson.setAddress(address);
//		return personService.savePerson(savePerson);
//	}
	
	@PostMapping("/person") 
	public void addPerson(@RequestBody Person person) throws StreamWriteException, DatabindException, IOException{
		personService.savePerson(person);		
	}
	
	@PutMapping("/person")
	public void updatePerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestBody Person person) {
		personService.updatePerson(person);
	}
	
	@DeleteMapping("/person")
	public void deletePerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
		personService.deletePerson(firstName, lastName);
	}
	
}

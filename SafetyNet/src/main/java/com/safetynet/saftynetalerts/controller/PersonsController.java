package com.safetynet.saftynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.saftynetalerts.model.Person;
import com.safetynet.saftynetalerts.repository.Persons;
import com.safetynet.saftynetalerts.service.PersonService;

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
	
	@GetMapping("/fire/address={adress}")
	public List<Person> getInhabitants(@PathVariable("adress") String address) {
		return personService.getInhabitants(address);
	}
	
//	@PostMapping("/person")
//	public Person createPerson(@RequestBody Person person) {
//		return personService.savePerson(person);
//	}
	
}

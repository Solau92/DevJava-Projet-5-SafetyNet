package com.safetynet.saftynetalerts.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.saftynetalerts.model.Person;
import com.safetynet.saftynetalerts.repository.Persons;

@RestController
public class Controller {
	
	private final Persons persons;
	
		public Controller(Persons persons) {
		this.persons = persons;
	}

//	@GetMapping("/persons")
//	public ResponseEntity<String> getPersons(){
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Liste des personnes :" );
//		}
		
		@GetMapping("/persons")
		public List<Person> getPersons(){
			return persons.getAllPersons();
			}
		
	}

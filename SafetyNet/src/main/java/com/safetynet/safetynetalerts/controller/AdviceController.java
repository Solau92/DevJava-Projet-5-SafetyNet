package com.safetynet.safetynetalerts.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.safetynet.safetynetalerts.exception.FirestationAlreadyExistsException;
import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.exception.MedicalRecordAlreadyExistsException;
import com.safetynet.safetynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonAlreadyExistsException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {
	
	private static final String MESSAGE = "message : ";
		
	@ExceptionHandler(PersonNotFoundException.class)
	public ResponseEntity<Object> personNotFoundExceptionHandler(PersonNotFoundException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PersonAlreadyExistsException.class)
	public ResponseEntity<Object> personAlreadyExistsExceptionHandler(PersonAlreadyExistsException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MedicalRecordNotFoundException.class)
	public ResponseEntity<Object> medicalRecordNotFoundExceptionHandler(MedicalRecordNotFoundException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MedicalRecordAlreadyExistsException.class)
	public ResponseEntity<Object> medicalRecordAlreadyExistsExceptionnHandler(MedicalRecordAlreadyExistsException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(FirestationNotFoundException.class)
	public ResponseEntity<Object> firestationNotFoundExceptionHandler(FirestationNotFoundException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FirestationAlreadyExistsException.class)
	public ResponseEntity<Object> firestationAlreadyExistsExceptionHandler(FirestationAlreadyExistsException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

}

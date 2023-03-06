package com.safetynet.saftynetalerts.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.safetynet.saftynetalerts.exception.FirestationAlreadyExistsException;
import com.safetynet.saftynetalerts.exception.FirestationNotFoundException;
import com.safetynet.saftynetalerts.exception.MedicalRecordAlreadyExistsException;
import com.safetynet.saftynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.saftynetalerts.exception.MoreThanOneFirestationFoundException;
import com.safetynet.saftynetalerts.exception.MoreThanOneMedicalRecordFoundException;
import com.safetynet.saftynetalerts.exception.MoreThanOnePersonFoundException;
import com.safetynet.saftynetalerts.exception.PersonAlreadyExistsException;
import com.safetynet.saftynetalerts.exception.PersonNotFoundException;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {
	
//	@ExceptionHandler(MyException.class) --> catch les exceptions de type MyException
//	@ResponseStatus(HttpStatus.NOT_FOUND) --> retourne le code HTTP Not found
//	public MyResponseType myExceptionHandler() {
//		return new MyResponseType();		--> renvoie message de type MyReponseTYpe
//	}
	
//	@ExceptionHandler(MyException.class) --> catch les exceptions de type MyException
//	@ResponseStatus(HttpStatus.NOT_FOUND) --> retourne le code HTTP Not found
//	public ResponseENtity<MyResponseType> myExceptionHandler() {
//		return new ResponseEntity<> (new MyResponseType(, HTTTPStatus.NOT_FOUND));		--> renvoie message de type MyReponseTYpe
//	}
	
//	@ExceptionHandler(NotFoundException.class)
//	public ResponseEntity<NotFoundException> notFoundExceptionHandler(NotFoundException nfex) {
//		return new ResponseEntity<>(new NotFoundException(), HttpStatus.NOT_FOUND);
//	}
	
	@ExceptionHandler(PersonNotFoundException.class)
	public ResponseEntity<Object> personNotFoundExceptionHandler(PersonNotFoundException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message : ", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PersonAlreadyExistsException.class)
	public ResponseEntity<Object> personAlreadyExistsExceptionHandler(PersonAlreadyExistsException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message : ", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MoreThanOnePersonFoundException.class)
	public ResponseEntity<Object> moreThanOnePersonFoundExceptionHandler(MoreThanOnePersonFoundException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message : ", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(MedicalRecordNotFoundException.class)
	public ResponseEntity<Object> medicalRecordNotFoundExceptionHandler(MedicalRecordNotFoundException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message : ", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MedicalRecordAlreadyExistsException.class)
	public ResponseEntity<Object> medicalRecordAlreadyExistsExceptionnHandler(MedicalRecordAlreadyExistsException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message : ", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MoreThanOneMedicalRecordFoundException.class)
	public ResponseEntity<Object> moreThanOneMedicalRecordFoundExceptionHandler(MoreThanOneMedicalRecordFoundException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message : ", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(FirestationNotFoundException.class)
	public ResponseEntity<Object> firestationNotFoundExceptionHandler(FirestationNotFoundException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message : ", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FirestationAlreadyExistsException.class)
	public ResponseEntity<Object> firestationAlreadyExistsExceptionHandler(FirestationAlreadyExistsException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message : ", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MoreThanOneFirestationFoundException.class)
	public ResponseEntity<Object> moreThanOneFirestationFoundExceptionHandler(MoreThanOneFirestationFoundException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message : ", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}

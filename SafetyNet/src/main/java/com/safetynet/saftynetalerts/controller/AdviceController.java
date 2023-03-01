package com.safetynet.saftynetalerts.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.safetynet.saftynetalerts.exception.NotFoundException;

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
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> notFoundExceptionHandler(NotFoundException nfex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", nfex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

}

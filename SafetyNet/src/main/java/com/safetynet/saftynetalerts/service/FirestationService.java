package com.safetynet.saftynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.model.Firestation;
import com.safetynet.saftynetalerts.repository.FirestationsRepository;

import jakarta.annotation.PostConstruct;
import lombok.Data;

@Service
public class FirestationService {
	
	/*
	 * @PostConstruct public void getToto() {
	 * 
	 * }
	 */

	@Autowired
	private FirestationsRepository firestations;
	
	public List<Firestation> getAllFirestations() {
		return firestations.getAllFirestations();
	}

}

package com.safetynet.saftynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.model.Firestation;
import com.safetynet.saftynetalerts.repository.Firestations;

import lombok.Data;

@Data
@Service
public class FirestationService {

	@Autowired
	private Firestations firestations;
	
	public List<Firestation> getAllFirestations() {
		return firestations.getAllFirestations();
	}

}

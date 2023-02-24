package com.safetynet.saftynetalerts.repository;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.safetynet.saftynetalerts.model.Firestation;

import lombok.Data;

@Repository
@Data
public class FirestationsRepository {
	
	private List<Firestation> firestations;

	public List<Firestation> getAllFirestations() {
		return firestations;
	}

}

package com.safetynet.saftynetalerts.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.safetynet.saftynetalerts.model.Firestation;

import lombok.Data;

@Component
@Data
public class Firestations {
	
	private List<Firestation> firestations;

}

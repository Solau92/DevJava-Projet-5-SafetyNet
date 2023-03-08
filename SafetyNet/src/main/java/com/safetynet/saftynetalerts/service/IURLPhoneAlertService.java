package com.safetynet.saftynetalerts.service;

import java.util.Set;

import com.safetynet.saftynetalerts.exception.FirestationNotFoundException;
import com.safetynet.saftynetalerts.exception.PersonNotFoundException;

public interface IURLPhoneAlertService {
	
	public Set<String> getPhoneAlert(int stationId) throws PersonNotFoundException, FirestationNotFoundException;
	
}

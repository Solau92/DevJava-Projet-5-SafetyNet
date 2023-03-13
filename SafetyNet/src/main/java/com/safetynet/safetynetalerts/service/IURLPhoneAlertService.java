package com.safetynet.safetynetalerts.service;

import java.util.Set;

import com.safetynet.safetynetalerts.exception.FirestationNotFoundException;
import com.safetynet.safetynetalerts.exception.PersonNotFoundException;

public interface IURLPhoneAlertService {
	
	public Set<String> getPhoneAlert(int stationId) throws PersonNotFoundException, FirestationNotFoundException;
	
}

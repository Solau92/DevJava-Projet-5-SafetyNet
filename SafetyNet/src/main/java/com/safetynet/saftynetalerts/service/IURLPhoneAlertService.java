package com.safetynet.saftynetalerts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.exception.PersonNotFoundException;

public interface IURLPhoneAlertService {
	
	public List<String> getPhoneAlert(int stationId) throws PersonNotFoundException;
	
}

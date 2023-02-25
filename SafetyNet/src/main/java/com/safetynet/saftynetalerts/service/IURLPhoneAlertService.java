package com.safetynet.saftynetalerts.service;

import java.util.List;

import org.springframework.stereotype.Service;

public interface IURLPhoneAlertService {
	
	public List<String> getPhoneAlert(int stationId);
	
}

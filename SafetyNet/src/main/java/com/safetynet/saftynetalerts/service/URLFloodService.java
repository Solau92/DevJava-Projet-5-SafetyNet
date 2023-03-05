package com.safetynet.saftynetalerts.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.model.DTOFlood;
import com.safetynet.saftynetalerts.model.DTOFloodFamily;
import com.safetynet.saftynetalerts.model.Person;

@Service
public class URLFloodService { //implements IURLFloodService {
	
	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IFirestationService firestationService;
	
	@Autowired
	private IMedicalRecordService medicalRecordService;
	
	public URLFloodService(IPersonService personService, IFirestationService firestationService, IMedicalRecordService medicalRecordService) {
		this.personService = personService;
		this.firestationService = firestationService;
		this.medicalRecordService = medicalRecordService;				
	}
	
//	@Override
	public List<DTOFlood> getFlood(List<Integer> stationIdList)  {

		List<DTOFlood> dtoFloodList = new ArrayList<DTOFlood>();
		
		///// Récupérer liste des adresses desservies par les casernes
		List<String> addressesList = new ArrayList<String>();
		
		// Je parcours la liste des stations
		for(Integer i : stationIdList) {
			
			// Pour chaque station, je récupère la liste des adresses
			List<String> address = firestationService.getAddressesWithId(i);		
			addressesList.addAll(address);		
		}
		
//		 Pour chaque adresse, récupérer la liste des foyers (DTOFloodFamily)
		for (String s : addressesList) {
				
			List<Person> personsList = personService.getPersonsByAddress(s);
			
			for(Person p : personsList) {
				
			}
//			
//
//			for(Person p : personsList) {
//				DTOFlood dtoFlood = new DTOFlood();
//				dtoFlood.getFloodAddresses().put(s, p.getLastName());
//
//			}
			
			
			DTOFloodFamily dtoFloodFamily = new DTOFloodFamily();
			
			// Crérer un DTOFlood
			// Créer une DTOListFamily
			// Créer une DTOListOfPersons
			
			// --> ajouter la liste de personnes à la DTOListFamily
			// --> ajouter la DTOListFamily à la DTOFlood
			// --> ajouter la DTOFlood à la ListDTOFlood
			
			
			// Pour chaque famille, récupérer la liste des personnes 

			
		}
			
		return dtoFloodList;
	}

}

package com.safetynet.saftynetalerts.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class URLFloodService implements IURLFloodService {
	
	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IFirestationService firestationService;
	
	@Autowired
	private IMedicalRecordService medicalRecordService;
	
//	@Override
//	public List<DTOFlood> getFlood(List<Integer> stationIdList)  {

//		List<DTOFlood> dtoFlood = new ArrayList<DTOFlood>();
//		
//		// Récupérer liste des adresses desservies par les casernes
//		Map<String, DTOFloodFamily> addressesList = new HashMap<String, DTOFloodFamily>();
//
//		for(Integer i : stationIdList) {
//			List<String> address = firestationService.getAddressesWithId(i);
//			
//			addressesList.putIfAbsent(null, null);
//			
//		}
//		
//		// Pour chaque adresse, récupérer la liste des foyers (DTOFloodFamily)
//		for (String s : addressesList) {
//			
//			
//			
//			// Pour chaque famille, récupérer la liste des personnes 
//
//			
//		}
//			
//		return null;
//	}

}

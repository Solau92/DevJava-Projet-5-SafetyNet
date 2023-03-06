package com.safetynet.saftynetalerts.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.safetynet.saftynetalerts.exception.FirestationNotFoundException;
import com.safetynet.saftynetalerts.exception.MedicalRecordNotFoundException;
import com.safetynet.saftynetalerts.exception.MoreThanOneMedicalRecordFoundException;
import com.safetynet.saftynetalerts.exception.PersonNotFoundException;
import com.safetynet.saftynetalerts.model.DTOFlood;
import com.safetynet.saftynetalerts.model.DTOFloodPerson;
import com.safetynet.saftynetalerts.model.MedicalRecord;
import com.safetynet.saftynetalerts.model.Person;

@Service
public class URLFloodService implements IURLFloodService {
	
	private final IPersonService personService;
	
	private final IFirestationService firestationService;
	
	private final IMedicalRecordService medicalRecordService;
	
	public URLFloodService(IPersonService personService, IFirestationService firestationService, IMedicalRecordService medicalRecordService) {
		this.personService = personService;
		this.firestationService = firestationService;
		this.medicalRecordService = medicalRecordService;				
	}
	
	@Override
	public List<DTOFlood> getFlood(List<Integer> stationIdList) throws PersonNotFoundException, MedicalRecordNotFoundException, MoreThanOneMedicalRecordFoundException, FirestationNotFoundException  {

		List<DTOFlood> dtoFloodList = new ArrayList<DTOFlood>();
		
		///// Récupérer liste des adresses desservies par les casernes
		List<String> addressesList = new ArrayList<String>();
		
		// Je parcours la liste des stations
		for(Integer i : stationIdList) {
			
			// Pour chaque station, je récupère la liste des adresses
			List<String> address = firestationService.getAddressesWithId(i);		
			addressesList.addAll(address);		// J'ai ma liste de toutes les adresses 

			//TODO : résoudre : chaque adresse apparait plusieurs fois...
		
//		 Pour chaque adresse, récupérer la liste des foyers (DTOFloodFamily)
		for (String s : addressesList) {
			
			// --> A chaque adresse j'ai un DTOFlood <adresse, families>
			DTOFlood dtoFlood = new DTOFlood();
			dtoFlood.setAddress(s);
				
			// Liste des personnes à cette adresse 
			List<Person> personsList = personService.getPersonsByAddress(s);
			
				// Je parcours la liste des personnes
				for(Person p : personsList) {
					
					// Si la famille n'existe pas déjà, la créer  
					if(!dtoFlood.getFamilyList().containsKey(p.getLastName())) {
						dtoFlood.getFamilyList().put(p.getLastName(), new ArrayList<DTOFloodPerson>());
					} 
					// Dans tous les cas, rajouter la personne dans la liste
					
					// Créer ma DTOFloodPerson
					DTOFloodPerson dtoPerson = new DTOFloodPerson();
					dtoPerson.setLastName(p.getLastName());
					dtoPerson.setPhone(p.getPhone());

					List<MedicalRecord> medicalRecords = medicalRecordService.getMedicalRecordsByFirstNameAndLastName(p.getFirstName(), p.getLastName());
					dtoPerson.setAge(ChronoUnit.YEARS.between(medicalRecords.get(0).getBirthdate(), LocalDate.now()));
					dtoPerson.setAllergies(medicalRecords.get(0).getAllergies());
					dtoPerson.setMedications(medicalRecords.get(0).getMedications());
					
					// Et l'ajouter à la liste 
					dtoFlood.getFamilyList().get(p.getLastName()).add(dtoPerson);

					}
				// Quand j'ai fini de parcourir la liste des personnes, toutes les personnes
				// à cette adresse sont mentionnées 
				// j'ajoute ma DTOFlood à la liste
				dtoFloodList.add(dtoFlood);
				}
		}		
						
		return dtoFloodList;
	}

}

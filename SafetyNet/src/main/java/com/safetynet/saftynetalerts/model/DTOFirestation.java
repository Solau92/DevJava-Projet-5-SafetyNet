package com.safetynet.saftynetalerts.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DTOFirestation {

	@JsonProperty("Persons")
	private List<DTOFirestationPerson> firestationPersons;
	
	@JsonProperty("numberOfAdults")
	private int numberOfAdults;
	
	@JsonProperty("numberOfChildren")
	private int numberOfChildren;	
	
	
	public void incrementNumberOfAdults() {
		this.numberOfAdults++;
	}
	
	public void incrementNumberOfChildren() {
		this.numberOfChildren++;
	}
	
}

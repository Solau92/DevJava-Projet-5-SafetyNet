package com.safetynet.saftynetalerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FirestationSpot {

	@JsonProperty("address")
	private String address;
	
	@JsonProperty("station")
	private int idStation;

}

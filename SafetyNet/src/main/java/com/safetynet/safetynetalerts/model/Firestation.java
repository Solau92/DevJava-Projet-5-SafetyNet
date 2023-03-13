package com.safetynet.safetynetalerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Firestation {

	@JsonProperty("address")
	private String address;
	
	@JsonProperty("station")
	private int idStation;

}

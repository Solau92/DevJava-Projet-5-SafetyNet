package com.safetynet.saftynetalerts.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FireStation {

	@JsonProperty("station")
	private int idStation;
	
	@JsonProperty("addresses")
	private List<String> addresses;
	
}

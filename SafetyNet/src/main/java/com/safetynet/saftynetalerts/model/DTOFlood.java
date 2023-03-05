package com.safetynet.saftynetalerts.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DTOFlood {
		
	@JsonProperty
	private String address;
	
	@JsonProperty
	private Map<String, List<DTOFloodPerson>> familyList;
	
	public DTOFlood() {
		this.familyList =  new HashMap<String, List<DTOFloodPerson>>();
	}
}

package com.safetynet.saftynetalerts.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DTOFire {

	@JsonProperty("Persons in building")
	private List<DTOFirePerson> personsInBuilding;
	
	@JsonProperty("stationId")
	private int stationId;
	
}

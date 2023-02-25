package com.safetynet.saftynetalerts.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DTOFire {

	@JsonProperty("personsInBuilding")
	private List<DTOFirePerson> personsInBuilding;
	
	@JsonProperty("stationId")
	private int stationId;
	
}

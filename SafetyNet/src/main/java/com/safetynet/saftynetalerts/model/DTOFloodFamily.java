package com.safetynet.saftynetalerts.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DTOFloodFamily {
	
//	// lastName / ListOfPersons
	@JsonProperty("familiesList")
//	private Map<String, List<DTOFloodPerson>> familiesList;
	private Map<String, List<Person>> familiesList;

}

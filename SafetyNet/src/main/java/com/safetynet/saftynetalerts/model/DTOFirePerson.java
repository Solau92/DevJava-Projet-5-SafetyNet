package com.safetynet.saftynetalerts.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DTOFirePerson {

	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty("age")
	private long age;
	
	@JsonProperty("medications")
	private List<String> medications;

	@JsonProperty("allergies")
	private List<String> allergies;
	
}

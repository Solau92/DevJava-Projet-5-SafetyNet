package com.safetynet.saftynetalerts.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DTOPersonInfo {

	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("address")
	private String address;
		
	@JsonProperty("age")
	private long age;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("medications")
	private List<String> medications;

	@JsonProperty("allergies")
	private List<String> allergies;
}

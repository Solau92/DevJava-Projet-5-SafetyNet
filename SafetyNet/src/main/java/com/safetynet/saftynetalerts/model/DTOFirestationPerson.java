package com.safetynet.saftynetalerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DTOFirestationPerson {

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("phone")
	private String phone;
}

package com.safetynet.saftynetalerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Person {
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("city")
	private String city;	
	
	@JsonProperty("zip")
	private String zipCode;
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty("email")
	private String email;

}

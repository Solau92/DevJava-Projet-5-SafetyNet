package com.safetynet.saftynetalerts.repository;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.saftynetalerts.model.Person;

public interface IDataPersonsReader {

	List<Person> readPersons() throws IOException;

}

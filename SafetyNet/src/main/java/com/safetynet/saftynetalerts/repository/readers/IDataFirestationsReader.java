package com.safetynet.saftynetalerts.repository.readers;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.saftynetalerts.model.FirestationSpot;
import com.safetynet.saftynetalerts.model.Person;

public interface IDataFirestationsReader {

	List<FirestationSpot> readFirestations() throws IOException;

}

package com.safetynet.saftynetalerts.repository;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.saftynetalerts.model.Person;

public class JSONPersonsDataWriter implements IDataPersonsWriter {

	String filePath;
	
	public JSONPersonsDataWriter(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public void writePerson(Person savedPerson) throws StreamWriteException, DatabindException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(new File(filePath), savedPerson);
	}
	

}

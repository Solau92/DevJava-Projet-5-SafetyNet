package com.safetynet.saftynetalerts.repository;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.safetynet.saftynetalerts.model.Person;

public interface IDataPersonsWriter {

	void writePerson(Person savedPerson) throws StreamWriteException, DatabindException, IOException;

}

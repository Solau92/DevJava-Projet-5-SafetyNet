package com.safetynet.safetynetalerts.repository.readers;

import java.io.IOException;
import java.util.List;

import com.safetynet.safetynetalerts.model.Person;

public interface IDataPersonsReader {

	List<Person> readPersons() throws IOException;

}

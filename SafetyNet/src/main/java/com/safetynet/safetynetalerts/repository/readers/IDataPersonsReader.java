package com.safetynet.safetynetalerts.repository.readers;

import java.io.IOException;
import java.util.List;

import com.safetynet.safetynetalerts.model.Person;

public interface IDataPersonsReader {

	/**
	 * Returns a list of persons.
	 * @return a list of persons
	 * @throws IOException
	 */
	List<Person> readPersons() throws IOException;

}

package com.safetynet.saftynetalerts.repository.readers;

import java.io.IOException;
import java.util.List;

import com.safetynet.saftynetalerts.model.Person;

public interface IDataPersonsReader {

	List<Person> readPersons() throws IOException;

}

package com.safetynet.safetynetalerts.repository.readers;

import java.io.IOException;
import java.util.List;

import com.safetynet.safetynetalerts.model.Firestation;

public interface IDataFirestationsReader {

	/**
	 * Returns a list of firestations.
	 * @return a list of firestations
	 * @throws IOException
	 */
	List<Firestation> readFirestations() throws IOException;

}

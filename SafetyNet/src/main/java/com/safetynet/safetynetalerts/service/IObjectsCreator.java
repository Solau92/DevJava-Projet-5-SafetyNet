package com.safetynet.safetynetalerts.service;

import java.io.IOException;

public interface IObjectsCreator {

	/**
	 * Reads a data source and extracts and sets the person respository, the
	 * firestation repository and the medical record repository.
	 * 
	 * @throws IOException
	 */
	void run() throws IOException;

}

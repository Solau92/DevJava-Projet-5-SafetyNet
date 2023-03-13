package com.safetynet.safetynetalerts.repository.readers;

import java.io.IOException;
import java.util.List;

import com.safetynet.safetynetalerts.model.Firestation;

public interface IDataFirestationsReader {

	List<Firestation> readFirestations() throws IOException;

}

package com.safetynet.saftynetalerts.repository.readers;

import java.io.IOException;
import java.util.List;

import com.safetynet.saftynetalerts.model.Firestation;

public interface IDataFirestationsReader {

	List<Firestation> readFirestations() throws IOException;

}

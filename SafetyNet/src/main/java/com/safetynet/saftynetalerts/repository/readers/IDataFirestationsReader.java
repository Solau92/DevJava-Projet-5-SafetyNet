package com.safetynet.saftynetalerts.repository.readers;

import java.io.IOException;
import java.util.List;

import com.safetynet.saftynetalerts.model.FirestationSpot;

public interface IDataFirestationsReader {

	List<FirestationSpot> readFirestations() throws IOException;

}

package com.safetynet.safetynetalerts.repository.readers;

import java.io.IOException;
import java.util.List;

import com.safetynet.safetynetalerts.model.MedicalRecord;

public interface IDataMedicalRecordsReader {

	/**
	 * Returns a list of medical records.
	 * @return a list of medical records.
	 * @throws IOException
	 */
	List<MedicalRecord> readMedicalRecords() throws IOException;

}

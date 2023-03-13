package com.safetynet.safetynetalerts.repository.readers;

import java.io.IOException;
import java.util.List;

import com.safetynet.safetynetalerts.model.MedicalRecord;

public interface IDataMedicalRecordsReader {

	List<MedicalRecord> readMedicalRecords() throws IOException;

}

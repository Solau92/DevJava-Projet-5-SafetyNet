package com.safetynet.saftynetalerts.repository;

import java.io.IOException;
import java.util.List;

import com.safetynet.saftynetalerts.model.MedicalRecord;

public interface IDataMedicalRecordsReader {

	List<MedicalRecord> readMedicalRecords() throws IOException;

}

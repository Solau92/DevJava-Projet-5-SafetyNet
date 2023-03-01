package com.safetynet.saftynetalerts.service;

import java.util.List;

import com.safetynet.saftynetalerts.model.DTOFlood;

public interface IURLFloodService {

	List<DTOFlood> getFlood(List<Integer> stationIdList);

}

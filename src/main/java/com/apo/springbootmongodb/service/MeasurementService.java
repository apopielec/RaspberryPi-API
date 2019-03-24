package com.apo.springbootmongodb.service;

import com.apo.springbootmongodb.model.Measurement;

public interface MeasurementService {

    void addMeasurement(Measurement measurement);
    void removeAllMeasurements();
    Measurement getLastMeasurement();
}

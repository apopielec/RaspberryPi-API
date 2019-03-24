package com.apo.springbootmongodb.service.impl;

import com.apo.springbootmongodb.model.Measurement;
import com.apo.springbootmongodb.repository.MeasurementRepository;
import com.apo.springbootmongodb.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementServiceImpl(MeasurementRepository repository) {
        this.measurementRepository = repository;
    }

    @Override
    public void addMeasurement(Measurement measurement) {
        measurementRepository.save(measurement);
    }

    @Override
    public Measurement getLastMeasurement() {
        List<Measurement> list = measurementRepository.findAll();
        return list.get(0);
    }

    @Override
    public void removeAllMeasurements() {
        measurementRepository.deleteAll();
    }
}

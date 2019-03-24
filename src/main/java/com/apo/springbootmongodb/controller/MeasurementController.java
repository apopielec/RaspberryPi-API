package com.apo.springbootmongodb.controller;

import com.apo.springbootmongodb.model.Measurement;
import com.apo.springbootmongodb.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping
    public Measurement getLastMeasurement(){
        return measurementService.getLastMeasurement();
    }

    @PostMapping
    public void addMeasurement(@RequestBody Measurement measurement){
        measurementService.removeAllMeasurements();
        measurementService.addMeasurement(measurement);
    }
}

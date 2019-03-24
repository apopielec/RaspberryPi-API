package com.apo.springbootmongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "measurements")
public class Measurement {

    @Id
    private String id;
    private long   timestamp;
    private double temperature;
    private double humidity;

    public Measurement(){
    }

    public Measurement(long timestamp, double temperature, double humidity) {
        this.timestamp   = timestamp;
        this.temperature = temperature;
        this.humidity    = humidity;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }
}

package com.exercise.trainlocator.domain;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    
    private Double[] coordinates;

    public Location() {

    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }

    // Digitraffic's coordinate data is mixed up, must swap places
    public void swapCoordinates() {
        Double temp = this.coordinates[0];
        this.coordinates[0] = this.coordinates[1];
        this.coordinates[1] = temp;
    }

    @Override
    public String toString() {
        return coordinates[0] + ", " + coordinates[1];
    }
}

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

    @Override
    public String toString() {
        return coordinates[0] + ", " + coordinates[1];
    }
}

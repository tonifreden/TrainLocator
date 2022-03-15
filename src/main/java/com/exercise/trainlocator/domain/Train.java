package com.exercise.trainlocator.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Train {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String destination;

    private Double speed;

    private Double[] coordinates;

    public Train() {

    }

    public Train(String name, String destination, Double speed, Double[] coordinates) {
        super();
        this.name = name;
        this.destination = destination;
        this.speed = speed;
        this.coordinates = coordinates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Destination: " + destination +
                ", Speed: " + speed + ", Coordinates: " + coordinates[0] + ", " + coordinates[1];
    }
}

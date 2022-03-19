package com.exercise.trainlocator.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomGenerator {
    final List<String> possibleNames = Arrays.asList("Trainy McTrainface", "Thomas the Tank Engine", "Intercity");
    final List<String> possibleDestinations = Arrays.asList("Helsinki", "Turku", "Tampere", "Oulu", "Jyväskylä", "Lappeenranta");
    Random random = new Random();

    private String name = possibleNames.get(random.nextInt(possibleNames.size())) + " ";

    private String destination = possibleDestinations.get(random.nextInt(possibleDestinations.size()));

    public RandomGenerator() {

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
}

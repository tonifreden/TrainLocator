package com.exercise.trainlocator.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TrainRepository extends CrudRepository<Train, Long> {
    List<Train> findByName(String name);
    
    List<Train> findByDestination(String destination);
}

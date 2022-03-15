package com.exercise.trainlocator.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findById(long id);
    
    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);
}

package com.exercise.trainlocator;

import static org.assertj.core.api.Assertions.assertThat;

import com.exercise.trainlocator.domain.Train;
import com.exercise.trainlocator.domain.TrainRepository;
import com.exercise.trainlocator.domain.User;
import com.exercise.trainlocator.domain.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AppRepositoryTests {
    
    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Test userRepository by finding user "admin" by username. Assert that 1) user is not null, 2) user's email is correct, 3) user's role is correct
     */
    @Test
    public void findByUsernameShouldReturnUser() {
        User user = userRepository.findByUsername("admin");
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo("admin@boss.com");
        assertThat(user.getRole()).isEqualTo("ADMIN");
    }

    /**
     * Test userRepository by creating new user. Assert that 1) user is not null, 2) user's username is correct, 3) user's last name is Dude
     */
    @Test
    public void createUser() {
        User user = new User("testuser", "Tester", "Dude", "$2a$10$220vEwCYQVaQAlbjUSQXqOeOZcGZBKQBxzPqvULV1L4Su1EP9kIvm", "tester@testmail.co.uk", "USER");
        userRepository.save(user);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("testuser");
        assertThat(user.getLastName()).isEqualTo("Dude");
    }

    /**
     * Test trainRepository by finding train by id. Assert that 1) train is not null, 2) train's Location has no null fields 3) train's speed is greater than or equal to 0.0
     */
    @Test
    public void findByIdShouldReturnTrain() {
        long id = 1;
        Train train = trainRepository.findById(Long.valueOf(id)).get();
        assertThat(train).isNotNull();
        assertThat(train.getLocation()).hasNoNullFieldsOrProperties();
        assertThat(train.getSpeed()).isGreaterThanOrEqualTo(0.0);
    }

    /**
     * Test trainRepository by creating a blank train. Assert that 1) train is not null, 2) a couple of train's attributes are null.
     */
    @Test
    public void createBlankTrain() {
        Train train = new Train();
        trainRepository.save(train);
        assertThat(train).isNotNull();
        assertThat(train.getName()).isNull();
        assertThat(train.getDestination()).isNull();
    }
}

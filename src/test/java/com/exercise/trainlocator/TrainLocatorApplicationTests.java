package com.exercise.trainlocator;

import static org.assertj.core.api.Assertions.assertThat;

import com.exercise.trainlocator.web.AppController;
import com.exercise.trainlocator.web.TrainController;
import com.exercise.trainlocator.web.UserController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TrainLocatorApplicationTests {

	@Autowired
	AppController appController;

	@Autowired
	TrainController trainController;

	@Autowired
	UserController userController;

	@Test
	void contextLoads() {
		assertThat(appController).isNotNull();
		assertThat(trainController).isNotNull();
		assertThat(userController).isNotNull();
	}

}

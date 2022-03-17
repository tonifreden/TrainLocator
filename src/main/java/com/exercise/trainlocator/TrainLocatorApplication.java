package com.exercise.trainlocator;

import com.exercise.trainlocator.domain.Train;
import com.exercise.trainlocator.domain.TrainRepository;
import com.exercise.trainlocator.domain.User;
import com.exercise.trainlocator.domain.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrainLocatorApplication {
	private static final Logger Log = LoggerFactory.getLogger(TrainLocatorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TrainLocatorApplication.class, args);
	}

	@Bean
	public CommandLineRunner trainDemo(TrainRepository trainRepository, UserRepository userRepository) {
		return (args) -> {
			Log.info("save a couple of trains and users");

			User user = new User("user", "Usery", "McUserface", "$2a$10$iLYJvJT2LsCLRF0TAWr1IeixohSPsFQw7.ElBdfThN9Jo.xXdHJ5G", "user@usermail.user", "USER");
			User admin = new User("admin", "Adminy", "McAdminface", "$2a$10$hpVpTvIG7kTUzYBZg4ED0exmW.HD1dSe6s.Z8nYaqJUGg/WdiYNvW", "admin@boss.com", "ADMIN");
			User toni = new User("toni", "Toni", "Asdasd", "$2a$10$eAvDmpV07TZnwqBW/oRlmuqQyduFLdInBEZ1elBxi/g1sXQ9dQgp6", "toni@admin.com", "ADMIN");
			userRepository.save(admin);
			userRepository.save(toni);
			userRepository.save(user);

			Double[] coords = {60.12, 12.60};
			Double[] coords2 = {70.12, 12.70};
			trainRepository.save(new Train("ASD", "Skulli", 0.12, coords));
			trainRepository.save(new Train("PRKL", "Perse", 2000.0, coords2));

			Log.info("fetch all trains");
			for (Train train : trainRepository.findAll()) {
				Log.info(train.toString());
			}
		};
	}
}

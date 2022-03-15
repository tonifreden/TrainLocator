package com.exercise.trainlocator;

import com.exercise.trainlocator.domain.Train;
import com.exercise.trainlocator.domain.TrainRepository;

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
	public CommandLineRunner trainDemo(TrainRepository trainRepository) {
		return (args) -> {
			Log.info("save a couple of trains");
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

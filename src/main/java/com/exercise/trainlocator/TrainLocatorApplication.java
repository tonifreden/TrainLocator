package com.exercise.trainlocator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.exercise.trainlocator.domain.Train;
import com.exercise.trainlocator.domain.TrainRepository;
import com.exercise.trainlocator.domain.User;
import com.exercise.trainlocator.domain.UserRepository;

import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

@SpringBootApplication
public class TrainLocatorApplication {
	private static final Logger Log = LoggerFactory.getLogger(TrainLocatorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TrainLocatorApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	// Method to fetch live data from digitraffic
	public Train[] getTrainData(RestTemplate restTemplate) {
		final HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build());
		restTemplate.setRequestFactory(clientHttpRequestFactory);
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Accept-Encoding", "gzip");
		HttpEntity<Train> entity = new HttpEntity<>(headers);
		ResponseEntity<Train[]> responseEntity = restTemplate.exchange("https://rata.digitraffic.fi/api/v1/train-locations/latest/", HttpMethod.GET, entity, Train[].class);
		return responseEntity.getBody();
	}

	@Bean
	public CommandLineRunner trainDemo(TrainRepository trainRepository, UserRepository userRepository, RestTemplate restTemplate) throws Exception {
		return (args) -> {
			Log.info("save a couple of demo users");

			userRepository.save(new User("admin", "Admin", "Guy", "$2a$10$hpVpTvIG7kTUzYBZg4ED0exmW.HD1dSe6s.Z8nYaqJUGg/WdiYNvW", "admin@boss.com", "ADMIN"));
			userRepository.save(new User("user", "User", "Fellow", "$2a$10$iLYJvJT2LsCLRF0TAWr1IeixohSPsFQw7.ElBdfThN9Jo.xXdHJ5G", "user@usermail.user", "USER"));

			// Fetch data
			Train[] trains = getTrainData(restTemplate);
			
			// Add random name and destination to each train, then save them to repo
			final List<String> possibleNames = Arrays.asList("Trainy McTrainface", "Thomas the Tank Engine", "Intercity");
			final List<String> possibleDestinations = Arrays.asList("Helsinki", "Turku", "Tampere", "Oulu", "Jyväskylä", "Lappeenranta");
			Random random = new Random();

			Log.info("save all trains to repo");
			for (Train train : trains) {
				train.setName(possibleNames.get(random.nextInt(possibleNames.size())) + " " + train.getTrainNumber());
				train.setDestination(possibleDestinations.get(random.nextInt(possibleDestinations.size())));
				trainRepository.save(train);
			}

			Log.info("fetch all trains from repo");
			for (Train train : trainRepository.findAll()) {
				Log.info(train.toString());
			}

			// Update data every 10 seconds
			Observable.interval(10, TimeUnit.SECONDS, Schedulers.io())
				.observeOn(Schedulers.newThread())
				.doOnError(error -> Log.info(error.toString()))
				.retry()
				.subscribe(s -> {
					Train[] updatedTrains = getTrainData(restTemplate);
					for (Train train : updatedTrains) {
						try {
							if (trainRepository.findByTrainNumber(train.getTrainNumber()) == null) {
								trainRepository.save(train);
							} else {
								Train oldTrain = trainRepository.findByTrainNumber(train.getTrainNumber()).get();
								oldTrain.setLocation(train.getLocation());
								oldTrain.setSpeed(train.getSpeed());
								trainRepository.save(oldTrain);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
		};
	}
}

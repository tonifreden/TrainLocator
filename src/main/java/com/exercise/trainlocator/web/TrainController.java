package com.exercise.trainlocator.web;

import java.util.List;
import java.util.Optional;

import com.exercise.trainlocator.domain.Train;
import com.exercise.trainlocator.domain.TrainRepository;
import com.exercise.trainlocator.domain.User;
import com.exercise.trainlocator.domain.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
public class TrainController {
    
    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private UserRepository userRepository;

    // Get all trains as a list in a Thymeleaf template
    @GetMapping("/")
    public String trainList(Model model, Authentication auth) { // lisää Authentication?
        model.addAttribute("trains", trainRepository.findAll());
        User user = userRepository.findByUsername(auth.getName());
        model.addAttribute("user", user);
        return "trainlist";
    }

    /****************** RESTFUL SERVICES ******************/

    // Get all trains
    @GetMapping("/api/trains")
    public @ResponseBody List<Train> trainListRest() {
        return (List<Train>) trainRepository.findAll();
    }

    @GetMapping("/api/trains/{id}")
    public @ResponseBody Optional<Train> findTrainRest(@PathVariable("id") Long id) {
        return trainRepository.findById(id);
    }

    @PutMapping("/api/trains/{id}/location")
    public Train putTrain(@RequestBody Train newTrain, @PathVariable("id") Long id) {
        return trainRepository.findById(id)
                    .map(train -> {
                        train.setTrainNumber(newTrain.getTrainNumber());
                        train.setName(newTrain.getName());
                        train.setDestination(newTrain.getDestination());
                        train.setLocation(newTrain.getLocation());
                        train.setSpeed(newTrain.getSpeed());
                        return trainRepository.save(train);
                    })
                    .orElseGet(() -> {
                        newTrain.setId(id);
                        return trainRepository.save(newTrain);
                    });
    }

    /******************************************************/
}

package com.exercise.trainlocator.web;

import com.exercise.trainlocator.domain.TrainRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrainController {
    
    @Autowired
    private TrainRepository trainRepository;

    @GetMapping("/")
    public String trainList(Model model) { // lisää Authentication?
        model.addAttribute("trains", trainRepository.findAll());
        return "trainlist";
    }
}

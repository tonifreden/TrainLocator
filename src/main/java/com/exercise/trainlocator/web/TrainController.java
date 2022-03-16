package com.exercise.trainlocator.web;

import com.exercise.trainlocator.domain.TrainRepository;
import com.exercise.trainlocator.domain.User;
import com.exercise.trainlocator.domain.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrainController {
    
    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String trainList(Model model, Authentication auth) { // lisää Authentication?
        model.addAttribute("trains", trainRepository.findAll());
        User user = userRepository.findByUsername(auth.getName());
        model.addAttribute("user", user);
        return "trainlist";
    }
}

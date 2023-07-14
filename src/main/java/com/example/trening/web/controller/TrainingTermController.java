package com.example.trening.web.controller;


import com.example.trening.service.TrainingTermService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/TrainingTerms")
public class TrainingTermController {

    private final TrainingTermService trainingTermService;

    public TrainingTermController(TrainingTermService trainingTermService) {
        this.trainingTermService = trainingTermService;
    }
}

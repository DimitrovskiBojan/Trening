package com.example.trening.web.controller;



import com.example.trening.model.Trainer;
import com.example.trening.model.exceptions.TrainerNotFoundException;
import com.example.trening.service.TrainerService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Trainers")
public class TrainersController {

    private final TrainerService trainerService;

    public TrainersController(TrainerService trainerService) {
        this.trainerService = trainerService;

    }

    @GetMapping
    public String getTrainersPage(@RequestParam(required = false) String error, Model model){

        String auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("user", auth);

        if (auth.equals("[ROLE_ANONYMOUS]")){
            return "home-page";
        }

        Trainer trainer =  (Trainer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long id = trainer.getId();

        Trainer loggedTrainer = this.trainerService.findById(id).orElseThrow(()-> new TrainerNotFoundException(id));

        model.addAttribute("loggedUser", loggedTrainer);

        List<Trainer> Trainers = this.trainerService.findAllImageNotNull();
        model.addAttribute("Trainers", Trainers);
        return "trainers-page";
    }
}

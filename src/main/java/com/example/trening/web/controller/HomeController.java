package com.example.trening.web.controller;

import com.example.trening.model.Trainer;
import com.example.trening.model.exceptions.TrainerNotFoundException;
import com.example.trening.service.TrainerService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private final TrainerService trainerService;

    public HomeController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping
    public String showHomePage(Model model){

        String auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("user", auth);

        if (auth.equals("[ROLE_ANONYMOUS]")){
            return "home-page";
        }

        Trainer trainer =  (Trainer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long id = trainer.getId();

        Trainer loggedTrainer = this.trainerService.findById(id).orElseThrow(()-> new TrainerNotFoundException(id));

        model.addAttribute("loggedUser", loggedTrainer);
        return "home-page";
    }

}

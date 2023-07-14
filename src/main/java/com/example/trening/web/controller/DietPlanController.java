package com.example.trening.web.controller;

import com.example.trening.model.DietPlan;
import com.example.trening.model.Trainer;
import com.example.trening.model.exceptions.DietPlanNotFoundException;
import com.example.trening.model.exceptions.TrainerNotFoundException;
import com.example.trening.service.DietPlanService;
import com.example.trening.service.TrainerService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@Controller
@RequestMapping("/DietPlans")
public class DietPlanController {

    private final DietPlanService dietPlanService;
    private final TrainerService trainerService;

    public DietPlanController(DietPlanService dietPlanService, TrainerService trainerService) {
        this.dietPlanService = dietPlanService;
        this.trainerService = trainerService;
    }


    @GetMapping
    public String getDietPlansPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<DietPlan> dietPlans = this.dietPlanService.findAll();
        model.addAttribute("dietPlans", dietPlans);
        return "master-template";
    }

    @GetMapping("/add-plan-page")
    public String addPlanPage(Model model){
        List<Trainer> trainers = this.trainerService.findAll();

        model.addAttribute("trainers", trainers);

        return "add-meal";
    }

    @PostMapping("/add-plan")
    public String addPlan(@RequestParam(required = false) Long id,
                          @RequestParam String type,
                          @RequestParam Long price,
                          @RequestParam long rate,
                          @RequestParam Long created_by,
                          @RequestParam String content,
                          @RequestParam String description
                          ){


        this.dietPlanService.save(price,type,created_by, description,content);

        return "redirect:/DietPlans";
    }

    @GetMapping("/show-more/{id}")
    public String showMore(@PathVariable Long id, Model model){

        DietPlan dietPlan = this.dietPlanService.findById(id).orElseThrow(() -> new DietPlanNotFoundException(id));

        model.addAttribute("plan", dietPlan);

        return "show-more-dietPlan";
    }



}

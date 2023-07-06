package com.example.trening.web.controller;

import com.example.trening.model.DietPlan;
import com.example.trening.service.DietPlanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/DietPlans")
public class DietPlanController {

    private final DietPlanService dietPlanService;

    public DietPlanController(DietPlanService dietPlanService) {
        this.dietPlanService = dietPlanService;
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

}

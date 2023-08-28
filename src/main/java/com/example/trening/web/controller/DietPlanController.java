package com.example.trening.web.controller;

import com.example.trening.model.DietPlan;
import com.example.trening.model.Trainer;
import com.example.trening.model.TrainingTerm;
import com.example.trening.model.exceptions.DietPlanAlreadyBought;
import com.example.trening.model.exceptions.DietPlanNotFoundException;
import com.example.trening.model.exceptions.TrainerNotFoundException;
import com.example.trening.model.exceptions.TrainingTermNotFoundException;
import com.example.trening.service.DietPlanService;
import com.example.trening.service.TrainerService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        String auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("user", auth);


        Trainer trainer =  (Trainer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long id = trainer.getId();

        Trainer loggedTrainer = this.trainerService.findById(id).orElseThrow(()-> new TrainerNotFoundException(id));

        model.addAttribute("loggedUser", loggedTrainer);

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
            return "diet-plans-page";
        }
        List<DietPlan> dietPlans = this.dietPlanService.findAll();
        model.addAttribute("dietPlans", dietPlans);
        return "diet-plans-page";
    }

    @GetMapping("/add-plan-page")
    public String addPlanPage(Model model){
        String auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("user", auth);

        if (auth.equals("[ROLE_ANONYMOUS]")){
            return "home-page";
        }

        Trainer trainer =  (Trainer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long id = trainer.getId();

        Trainer loggedTrainer = this.trainerService.findById(id).orElseThrow(()-> new TrainerNotFoundException(id));

        model.addAttribute("loggedUser", loggedTrainer);
        List<Trainer> trainers = this.trainerService.findAll();

        model.addAttribute("trainers", trainers);

        return "add-meal";
    }

    @PostMapping("/add-plan")
    public String addPlan(@RequestParam(required = false) Long id,
                          @RequestParam String type,
                          @RequestParam Long price,


                          @RequestParam String content,
                          @RequestParam String description
                          ){

        Trainer trainer =  (Trainer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long created_by = trainer.getId();

        this.dietPlanService.save(price,type,created_by, description,content);

        return "redirect:/DietPlans";
    }
    @GetMapping("/edit-dietPlan/{id}")
    public String showEditPage(@PathVariable Long id, Model model){

        String auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("user", auth);

        if (auth.equals("[ROLE_ANONYMOUS]")){
            return "home-page";
        }

        Trainer trainer =  (Trainer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long idd = trainer.getId();

        Trainer loggedTrainer = this.trainerService.findById(idd).orElseThrow(()-> new TrainerNotFoundException(idd));

        model.addAttribute("loggedUser", loggedTrainer);


        DietPlan dietPlan = this.dietPlanService.findById(id).orElseThrow(()-> new DietPlanNotFoundException(id));

        model.addAttribute("dietPlan", dietPlan);


        return "edit-dietPlan-page";
    }

    @GetMapping("/show-more/{id}")
    public String showMore(@RequestParam(required = false) String error, @PathVariable Long id, Model model){

        String auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("user", auth);

        if (auth.equals("[ROLE_ANONYMOUS]")){
            return "home-page";
        }

        Trainer trainer =  (Trainer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long idd = trainer.getId();

        Trainer loggedTrainer = this.trainerService.findById(idd).orElseThrow(()-> new TrainerNotFoundException(idd));

        model.addAttribute("loggedUser", loggedTrainer);

        DietPlan dietPlan = this.dietPlanService.findById(id).orElseThrow(() -> new DietPlanNotFoundException(id));

        model.addAttribute("plan", dietPlan);

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
            return "show-more-dietPlan";
        }

        return "show-more-dietPlan";
    }

    @GetMapping("/my-diet-plans-trainer")
    private String myDietPlansTrainer(Model model){

        String auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("user", auth);

        if (auth.equals("[ROLE_ANONYMOUS]")){
            return "home-page";
        }

        Trainer trainer =  (Trainer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long id = trainer.getId();

        Trainer loggedTrainer = this.trainerService.findById(id).orElseThrow(()-> new TrainerNotFoundException(id));

        List<DietPlan> dietPlans = this.dietPlanService.findAllByCreated_by(loggedTrainer);

        model.addAttribute("loggedUser", loggedTrainer);
        model.addAttribute("dietPlans", dietPlans);

        return "my-diet-plans-trainer";
    }
    @GetMapping("/my-diet-plans-client")
    private String myDietPlansClient(Model model){

        String auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("user", auth);

        if (auth.equals("[ROLE_ANONYMOUS]")){
            return "home-page";
        }
        Trainer trainer =  (Trainer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long id = trainer.getId();

        Trainer loggedTrainer = this.trainerService.findById(id).orElseThrow(()-> new TrainerNotFoundException(id));

        List<DietPlan> dietPlans = this.dietPlanService.getAllPlansForTrainer(id);

        model.addAttribute("loggedUser", loggedTrainer);
        model.addAttribute("dietPlans", dietPlans);

        return "my-diet-plans-client";
    }

    @PostMapping("/add-plan-to-client")
    private String addPlanToClient(@RequestParam Long planId, Model model){

        String auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if (auth.equals("[ROLE_ANONYMOUS]")){
            return "home-page";
        }
        Trainer trainer =  (Trainer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long id = trainer.getId();

        Trainer loggedTrainer = this.trainerService.findById(id).orElseThrow(()-> new TrainerNotFoundException(id));

        try {
            this.dietPlanService.addPlanToClient(id,planId);
        }
        catch (DietPlanAlreadyBought exception){

            return "redirect:/DietPlans/show-more/"+planId+"?error="+ exception.getMessage();
        }

        return "redirect:/DietPlans/my-diet-plans-client";

    }



    @PostMapping("/edit")
    public String edit(@RequestParam(required = false) Long planId,
                       @RequestParam String type,
                       @RequestParam Long price,


                       @RequestParam String content,
                       @RequestParam String description){

        Trainer trainer =  (Trainer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long created_by = trainer.getId();

        this.dietPlanService.edit(planId,price,type,created_by,description,content);

        return "redirect:/DietPlans/my-diet-plans-trainer";
    }

    @PostMapping("/delete-dietPlan/{id}")
    public String deleteTerm(@PathVariable Long id){
        DietPlan dietPlan = dietPlanService.findById(id).orElseThrow(() -> new TrainingTermNotFoundException(id));

        Trainer trainer = this.trainerService.findAllByPlanContaining(dietPlan);

        if(trainer != null){
            this.dietPlanService.removePlanFromClient(trainer.getId(),dietPlan.getId());
        }
        this.dietPlanService.deleteById(id);

        return "redirect:/DietPlans/my-diet-plans-trainer";
    }

    @PostMapping("/like/{id}")
    public String like(@PathVariable long id){
        this.dietPlanService.like(id);
        return "redirect:/DietPlans/my-diet-plans-client";
    }
    @PostMapping("/unlike/{id}")
    public String unlike(@PathVariable long id){
        this.dietPlanService.unlike(id);
        return "redirect:/DietPlans/my-diet-plans-client";
    }


}

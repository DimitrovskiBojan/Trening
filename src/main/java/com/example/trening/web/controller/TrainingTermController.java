package com.example.trening.web.controller;

import com.example.trening.model.DietPlan;
import com.example.trening.model.Trainer;
import com.example.trening.model.TrainingTerm;
import com.example.trening.model.exceptions.DietPlanAlreadyBought;
import com.example.trening.model.exceptions.TrainerNotFoundException;
import com.example.trening.model.exceptions.TrainingTermNotFoundException;
import com.example.trening.service.TrainerService;
import com.example.trening.service.TrainingTermService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.List;

@Controller
@RequestMapping("/TrainingTerm")
public class TrainingTermController {

    private final TrainerService trainerService;
    private final TrainingTermService trainingTermService;

    public TrainingTermController(TrainerService trainerService, TrainingTermService trainingTermService) {
        this.trainerService = trainerService;
        this.trainingTermService = trainingTermService;
    }

    @GetMapping
    public String getTrainingTermsPage(@RequestParam(required = false) String error, Model model){



        List <TrainingTerm> trainingTerms = this.trainingTermService.findAll();
        model.addAttribute("terms", trainingTerms);
        return "terms-page";
    }

    @GetMapping("/show-add-new")
    public String showAddNewTrainingTerm(@RequestParam Long trainerId, Model model){

        String auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("user", auth);

        if (auth.equals("[ROLE_ANONYMOUS]")){
            return "home-page";
        }

        Trainer trainer =  (Trainer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long id = trainer.getId();

        Trainer loggedTrainer = this.trainerService.findById(id).orElseThrow(()-> new TrainerNotFoundException(id));

        model.addAttribute("loggedUser", loggedTrainer);

        model.addAttribute("trainerId", trainerId);

        return "add-term-page";
    }

    @PostMapping("/add-new")
    public String AddNewTrainingTerm(@RequestParam String date,
                                     @RequestParam String start_time,
                                     @RequestParam String end_time,
                                     @RequestParam Long price,
                                     @RequestParam Long trainerId
                                     ){
        this.trainingTermService.save(date,start_time,end_time,price,trainerId);

        Long rID = trainerId;

        return "redirect:/TrainingTerm/my-training-terms-trainer";
    }

    @GetMapping("/my-training-terms-trainer")
    private String myTrainingTermsTrainer(Model model){
        String auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("user", auth);

        if (auth.equals("[ROLE_ANONYMOUS]")){
            return "home-page";
        }

        Trainer trainer =  (Trainer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long id = trainer.getId();

        Trainer loggedTrainer = this.trainerService.findById(id).orElseThrow(()-> new TrainerNotFoundException(id));

        List<TrainingTerm> trainingTerms = this.trainingTermService.findAllByCreated_by(loggedTrainer);

        model.addAttribute("loggedUser", loggedTrainer);
        model.addAttribute("trainingTerms", trainingTerms);

        return "my-training-terms-trainer";
    }


    @GetMapping("/show-terms/{id}")
    public String showMore(@PathVariable Long id, Model model, HttpServletRequest request){

        String auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("user", auth);

        if (auth.equals("[ROLE_ANONYMOUS]")){
            return "home-page";
        }

        Trainer trainer =  (Trainer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long trainerId = trainer.getId();

        Trainer loggedTrainer = this.trainerService.findById(trainerId).orElseThrow(()-> new TrainerNotFoundException(trainerId));

        model.addAttribute("loggedUser", loggedTrainer);

        List<TrainingTerm> trainingTerms = trainingTermService.findAllByTrainers(id);
        Trainer trainerr = trainerService.findById(id).orElseThrow(() -> new TrainerNotFoundException(id));



        model.addAttribute("terms", trainingTerms);
        model.addAttribute("trainer", trainerr);
        String authh = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("username", authh);

        return "terms-page";
    }

    @GetMapping("/edit-page/{id}")
    public String showEditPage(@PathVariable Long id, @RequestParam Long trainerId, Model model){

        String auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("user", auth);

        if (auth.equals("[ROLE_ANONYMOUS]")){
            return "home-page";
        }

        Trainer trainer =  (Trainer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long trainerIdd = trainer.getId();

        Trainer loggedTrainer = this.trainerService.findById(trainerIdd).orElseThrow(()-> new TrainerNotFoundException(trainerIdd));

        model.addAttribute("loggedUser", loggedTrainer);

        TrainingTerm trainingTerm = this.trainingTermService.findById(id).orElseThrow(() -> new TrainingTermNotFoundException(id));

        model.addAttribute("trainingTerm", trainingTerm);
        model.addAttribute("trainerId", trainerId);

        return "edit-page";
    }

    @PostMapping("/delete/{id}")
    public String deleteTerm(@PathVariable Long id, @RequestParam Long redirectId){
        Long rID = redirectId;

        TrainingTerm trainingTerm = trainingTermService.findById(id).orElseThrow(() -> new TrainingTermNotFoundException(id));

        Trainer trainer = this.trainerService.findAllByTermContaining(trainingTerm);

        if(trainer != null){
            this.trainingTermService.removeTermFromClient(trainer.getId(),trainingTerm.getId());
        }
        this.trainingTermService.deleteById(id);

        return "redirect:/TrainingTerm/my-training-terms-trainer";

    }
    @PostMapping("/edit")
    public String edit(@RequestParam String date,
                       @RequestParam String start_time,
                       @RequestParam String end_time,
                       @RequestParam Long price,
                       @RequestParam Long termId,
                       @RequestParam Long trainerId){
        this.trainingTermService.edit(termId,date,start_time,end_time,price,trainerId);

        Long rID = trainerId;

        return "redirect:/TrainingTerm/my-training-terms-trainer";

    }

    @GetMapping("/my-training-terms-client")
    public String myTrainingTermsClient(Model model){

        String auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("user", auth);

        if (auth.equals("[ROLE_ANONYMOUS]")){
            return "home-page";
        }

        Trainer trainer =  (Trainer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long id = trainer.getId();

        Trainer loggedTrainer = this.trainerService.findById(id).orElseThrow(()-> new TrainerNotFoundException(id));

        List<TrainingTerm> trainingTerms = this.trainingTermService.findAllTermsForTrainer(id);

        model.addAttribute("loggedUser", loggedTrainer);
        model.addAttribute("trainingTerms", trainingTerms);

        return "my-training-terms-client";
    }

    @PostMapping("/add-term-to-client")
    private String addTermToClient(@RequestParam Long termId, Model model){

        String auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if (auth.equals("[ROLE_ANONYMOUS]")){
            return "home-page";
        }
        Trainer trainer =  (Trainer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long id = trainer.getId();

        Trainer loggedTrainer = this.trainerService.findById(id).orElseThrow(()-> new TrainerNotFoundException(id));


        this.trainingTermService.addTermToClient(id,termId);
        this.trainingTermService.take(termId);

        model.addAttribute("loggedUser", loggedTrainer);


        return "redirect:/TrainingTerm/my-training-terms-client";

    }

    @PostMapping("/remove-term-from-client")
    public String removeTermFromClient(@RequestParam Long termId, Model model){
        String auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if (auth.equals("[ROLE_ANONYMOUS]")){
            return "home-page";
        }
        Trainer trainer =  (Trainer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long id = trainer.getId();

        Trainer loggedTrainer = this.trainerService.findById(id).orElseThrow(()-> new TrainerNotFoundException(id));
        this.trainingTermService.removeTermFromClient(id,termId);


        model.addAttribute("loggedUser", loggedTrainer);

        return "redirect:/TrainingTerm/my-training-terms-client";


    }
}
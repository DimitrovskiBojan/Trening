package com.example.trening.service;


import com.example.trening.model.DietPlan;
import com.example.trening.model.Trainer;
import com.example.trening.model.TrainingTerm;
import com.example.trening.model.exceptions.DietPlanNotFoundException;
import com.example.trening.model.exceptions.TrainerNotFoundException;
import org.w3c.dom.Text;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface DietPlanService {

    List<DietPlan> findAll();

    Optional<DietPlan> findById(Long id);

    Optional<DietPlan> save(Long price, String type, Long created_by, String description, String content);

    Optional<DietPlan> edit(Long id, Long price, String type, Long created_by, String description, String content);

    List<DietPlan> findAllByCreated_by(Trainer trainer);

    void addPlanToClient(Long trainerId, Long dietId);

    List<DietPlan> getAllPlansForTrainer(Long trainerId);

    void removePlanFromClient(Long trainerId, Long planId);


    void like(Long planId);
    void unlike(Long planId);
    

    void deleteById(Long id);

}

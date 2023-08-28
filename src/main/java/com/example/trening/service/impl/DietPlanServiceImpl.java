package com.example.trening.service.impl;

import com.example.trening.model.DietPlan;
import com.example.trening.model.Trainer;
import com.example.trening.model.TrainingTerm;
import com.example.trening.model.exceptions.DietPlanAlreadyBought;
import com.example.trening.model.exceptions.DietPlanNotFoundException;
import com.example.trening.model.exceptions.TrainerNotFoundException;
import com.example.trening.repository.DietPlanRepository;
import com.example.trening.repository.TrainerRepository;
import com.example.trening.service.DietPlanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
public class DietPlanServiceImpl implements DietPlanService {

    private final DietPlanRepository dietPlanRepository;
    private final TrainerRepository trainerRepository;

    public DietPlanServiceImpl(DietPlanRepository dietPlanRepository, TrainerRepository trainerRepository) {
        this.dietPlanRepository = dietPlanRepository;
        this.trainerRepository = trainerRepository;

    }

    @Override
    public List<DietPlan> findAll() {
        return this.dietPlanRepository.findAll();
    }

    @Override
    public Optional<DietPlan> findById(Long id) {
        return this.dietPlanRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<DietPlan> save(Long price, String type, Long created_by, String description, String content) {

        Trainer trainer = this.trainerRepository.findById(created_by).orElseThrow(()->new TrainerNotFoundException(created_by));

        DietPlan dietPlan = new DietPlan(price,type,trainer,description,content);
        this.dietPlanRepository.save(dietPlan);
        return Optional.of(dietPlan);
    }

    @Override
    public Optional<DietPlan> edit(Long id, Long price, String type, Long created_by, String description, String content) {

        Trainer trainer = this.trainerRepository.findById(created_by).orElseThrow(()->new TrainerNotFoundException(created_by));

        DietPlan dietPlan = this.dietPlanRepository.findById(id).orElseThrow(() -> new DietPlanNotFoundException(id));

        dietPlan.setPrice(price);
        dietPlan.setType(type);
        dietPlan.setCreated_by(trainer);
        dietPlan.setDescription(description);
        dietPlan.setContent(content);

        this.dietPlanRepository.save(dietPlan);

        return Optional.of(dietPlan);
    }

    @Override
    public List<DietPlan> findAllByCreated_by(Trainer trainer) {
        return this.dietPlanRepository.findAllByCreated_by(trainer);
    }

    @Override
    public void addPlanToClient(Long trainerId, Long dietId) {
        Trainer trainer = this.trainerRepository.findById(trainerId).orElseThrow(()->new TrainerNotFoundException(trainerId));

        DietPlan dietPlan = this.dietPlanRepository.findById(dietId).orElseThrow(() -> new DietPlanNotFoundException(dietId));

        if(trainer.getPlans().contains(dietPlan)){
            throw new DietPlanAlreadyBought(dietId);
        }
        else {
            trainer.getPlans().add(dietPlan);

            this.trainerRepository.save(trainer);
        }


    }

    @Override
    public List<DietPlan> getAllPlansForTrainer(Long trainerId) {

        Trainer trainer = this.trainerRepository.findById(trainerId).orElseThrow(()->new TrainerNotFoundException(trainerId));

        return trainer.getPlans();
    }

    @Override
    public void removePlanFromClient(Long trainerId, Long planId) {
        Trainer trainer = this.trainerRepository.findById(trainerId).orElseThrow(()->new TrainerNotFoundException(trainerId));

        DietPlan dietPlan = this.dietPlanRepository.findById(planId).orElseThrow(() -> new DietPlanNotFoundException(planId));

        trainer.getPlans().remove(dietPlan);

        this.trainerRepository.save(trainer);
    }


    @Override
    public void like(Long planId) {
        DietPlan dietPlan = this.dietPlanRepository.findById(planId).orElseThrow(()-> new DietPlanNotFoundException(planId));

        long likes = dietPlan.getRate();

        likes++;

        dietPlan.setRate(likes);

        this.dietPlanRepository.save(dietPlan);

    }

    @Override
    public void unlike(Long planId) {

        DietPlan dietPlan = this.dietPlanRepository.findById(planId).orElseThrow(()-> new DietPlanNotFoundException(planId));

        long likes = dietPlan.getRate();

        likes--;

        dietPlan.setRate(likes);

        this.dietPlanRepository.save(dietPlan);

    }

    @Override
    public void deleteById(Long id) {
        this.dietPlanRepository.deleteById(id);
    }
}

package com.example.trening.service.impl;

import com.example.trening.model.DietPlan;
import com.example.trening.model.Trainer;
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
    public void deleteById(Long id) {
        this.dietPlanRepository.deleteById(id);
    }
}

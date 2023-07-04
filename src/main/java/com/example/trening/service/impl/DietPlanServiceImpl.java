package com.example.trening.service.impl;

import com.example.trening.model.DietPlan;
import com.example.trening.model.exceptions.DietPlanNotFoundException;
import com.example.trening.repository.DietPlanRepository;
import com.example.trening.service.DietPlanService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DietPlanServiceImpl implements DietPlanService {

    private final DietPlanRepository dietPlanRepository;

    public DietPlanServiceImpl(DietPlanRepository dietPlanRepository) {
        this.dietPlanRepository = dietPlanRepository;
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
    public Optional<DietPlan> save(String cena) {
        DietPlan dietPlan = new DietPlan(cena);
        this.dietPlanRepository.save(dietPlan);
        return Optional.of(dietPlan);
    }

    @Override
    public Optional<DietPlan> edit(Long id, String cena) {

        DietPlan dietPlan = this.dietPlanRepository.findById(id).orElseThrow(() -> new DietPlanNotFoundException(id));

        dietPlan.setCena(cena);

        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        this.dietPlanRepository.deleteById(id);
    }
}

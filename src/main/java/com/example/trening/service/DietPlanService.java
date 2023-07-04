package com.example.trening.service;


import com.example.trening.model.DietPlan;

import java.util.List;
import java.util.Optional;

public interface DietPlanService {

    List<DietPlan> findAll();

    Optional<DietPlan> findById(Long id);

    Optional<DietPlan> save(String cena);

    Optional<DietPlan> edit(Long id, String cena);
    

    void deleteById(Long id);

}

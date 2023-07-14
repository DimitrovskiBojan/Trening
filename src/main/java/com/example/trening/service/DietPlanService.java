package com.example.trening.service;


import com.example.trening.model.DietPlan;
import com.example.trening.model.Trainer;
import org.w3c.dom.Text;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface DietPlanService {

    List<DietPlan> findAll();

    Optional<DietPlan> findById(Long id);

    Optional<DietPlan> save(Long price, String type, Long created_by, String description, String content);

    Optional<DietPlan> edit(Long id, Long price, String type, Long created_by, String description, String content);
    

    void deleteById(Long id);

}

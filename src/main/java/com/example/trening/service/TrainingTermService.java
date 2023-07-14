package com.example.trening.service;

import com.example.trening.model.DietPlan;
import com.example.trening.model.Trainer;
import com.example.trening.model.TrainingTerm;

import java.util.List;
import java.util.Optional;

public interface TrainingTermService {


    List<TrainingTerm> findAll();

    Optional<TrainingTerm> findById(Long id);

    Optional<TrainingTerm> save(String datum, String start, String end_time, Long price, Long trainer);

    Optional<TrainingTerm> edit(Long id, String datum, String start, String end_time, Long price, Long trainer);


    void deleteById(Long id);

}

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

    List<TrainingTerm> findAllByTrainers(Long id);

    List<TrainingTerm> findAllByCreated_by(Trainer trainer);

    List<TrainingTerm> findAllByNotTaken(Trainer trainer);
    void addTermToClient(Long trainerId, Long trainingTermId);
    void removeTermFromClient(Long trainerId, Long termId);

    List<TrainingTerm> findAllTermsForTrainer(Long trainerId);

    void deleteById(Long id);

    void take(Long id);
    void untake(Long id);


}

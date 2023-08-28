package com.example.trening.repository;


import com.example.trening.model.DietPlan;
import com.example.trening.model.Trainer;
import com.example.trening.model.TrainingTerm;
import com.example.trening.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Optional<Trainer> findByUsername(String username);

    Optional<Trainer> findByUsernameAndPassword(String username, String password);

    List<Trainer> findAllByImageNotNull();

    Trainer findByTermsContaining(TrainingTerm term);

    Trainer findByPlansContaining(DietPlan dietPlan);

}

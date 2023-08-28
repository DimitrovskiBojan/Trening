package com.example.trening.service;

import com.example.trening.model.DietPlan;
import com.example.trening.model.Trainer;
import com.example.trening.model.TrainingTerm;
import com.example.trening.model.User;
import com.example.trening.model.enumeration.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface TrainerService extends UserDetailsService {

    Trainer register(String username, String name, String surname, int age, String password, String card_number, String expiration_date, String CVV, String bank_name, String image, Role role);

    Trainer UserRegister(String username, String name, String surname, int age, long height, long weight, String password,
                  String card_number, String expiration_date, String CVV, String bank_name, Role role);
    List<Trainer> findAll();

    Optional<Trainer> findById(Long id);

    List<Trainer> findAllImageNotNull();

    Trainer findAllByTermContaining(TrainingTerm term);

    Trainer findAllByPlanContaining(DietPlan plan);

}

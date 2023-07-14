package com.example.trening.service;

import com.example.trening.model.DietPlan;
import com.example.trening.model.Trainer;

import java.util.List;
import java.util.Optional;

public interface TrainerService {

    Trainer register(String username, String name, String surname, int age, String password, String card_number, String expiration_date, String CVV, String bank_name);

    List<Trainer> findAll();

    Optional<Trainer> findById(Long id);

}

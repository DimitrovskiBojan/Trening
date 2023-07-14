package com.example.trening.service.impl;

import com.example.trening.model.Trainer;
import com.example.trening.repository.TrainerRepository;
import com.example.trening.service.TrainerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;

    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Trainer register(String username, String name, String surname, int age, String password, String card_number, String expiration_date, String CVV, String bank_name) {
        return null;
    }

    @Override
    public List<Trainer> findAll() {
        return this.trainerRepository.findAll();
    }

    @Override
    public Optional<Trainer> findById(Long id) {
        return this.trainerRepository.findById(id);
    }
}

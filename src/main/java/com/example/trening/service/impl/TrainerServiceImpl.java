package com.example.trening.service.impl;

import com.example.trening.model.DietPlan;
import com.example.trening.model.Trainer;
import com.example.trening.model.TrainingTerm;
import com.example.trening.model.User;
import com.example.trening.model.enumeration.Role;
import com.example.trening.model.exceptions.InvalidUsernameOrPasswordException;
import com.example.trening.model.exceptions.UsernameAlreadyExistsException;
import com.example.trening.repository.TrainerRepository;
import com.example.trening.service.TrainerService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;
    private final PasswordEncoder passwordEncoder;

    public TrainerServiceImpl(TrainerRepository trainerRepository, PasswordEncoder passwordEncoder) {
        this.trainerRepository = trainerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Trainer register(String username, String name, String surname, int age, String password, String card_number, String expiration_date, String CVV, String bank_name, String image, Role role) {
        Trainer trainer = new Trainer(username, name, surname, age, passwordEncoder.encode(password), card_number, expiration_date, CVV, bank_name, image,role);
        return this.trainerRepository.save(trainer);
    }

    @Override
    public Trainer UserRegister(String username, String name, String surname, int age, long height, long weight, String password, String card_number, String expiration_date, String CVV, String bank_name, Role role) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if(this.trainerRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);

        Trainer user = new Trainer(username,name,surname,age,passwordEncoder.encode(password),weight,height,card_number,expiration_date,CVV,bank_name,role);

        return trainerRepository.save(user);
    }

    @Override
    public List<Trainer> findAll() {
        return this.trainerRepository.findAll();
    }

    @Override
    public Optional<Trainer> findById(Long id) {
        return this.trainerRepository.findById(id);

    }

    @Override
    public List<Trainer> findAllImageNotNull() {
        return this.trainerRepository.findAllByImageNotNull();
    }

    @Override
    public Trainer findAllByTermContaining(TrainingTerm term) {
        return this.trainerRepository.findByTermsContaining(term);
    }

    @Override
    public Trainer findAllByPlanContaining(DietPlan plan) {
        return this.trainerRepository.findByPlansContaining(plan);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return trainerRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(username));
    }
}

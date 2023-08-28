package com.example.trening.service.impl;

import com.example.trening.model.Trainer;
import com.example.trening.model.User;
import com.example.trening.model.exceptions.InvalidArgumentsException;
import com.example.trening.model.exceptions.InvalidUserCredentialsException;
import com.example.trening.repository.TrainerRepository;
import com.example.trening.repository.UserRepository;
import com.example.trening.service.AuthService;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final TrainerRepository trainerRepository;

    public AuthServiceImpl(UserRepository userRepository, TrainerRepository trainerRepository) {
        this.userRepository = userRepository;
        this.trainerRepository = trainerRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public Trainer TrainerLogin(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return trainerRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }


}



package com.example.trening.service;

import com.example.trening.model.Trainer;
import com.example.trening.model.User;

public interface AuthService {
    User login(String username, String password);
    Trainer TrainerLogin(String username, String password);
}

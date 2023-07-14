package com.example.trening.service;

import com.example.trening.model.DietPlan;
import com.example.trening.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User register(String username, String name, String surname, int age, long height, long weight, String password, String card_number, String expiration_date, String CVV, String bank_name);

}

package com.example.trening.service;

import com.example.trening.model.DietPlan;
import com.example.trening.model.User;
import com.example.trening.model.enumeration.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    User register(String username, String name, String surname, int age, long height, long weight, String password,
                  String card_number, String expiration_date, String CVV, String bank_name, Role role);

}

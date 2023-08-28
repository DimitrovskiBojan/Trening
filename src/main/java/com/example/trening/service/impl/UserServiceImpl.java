package com.example.trening.service.impl;

import com.example.trening.model.User;
import com.example.trening.model.enumeration.Role;
import com.example.trening.model.exceptions.InvalidUsernameOrPasswordException;
import com.example.trening.model.exceptions.UsernameAlreadyExistsException;
import com.example.trening.repository.UserRepository;
import com.example.trening.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username,
                         String name,
                         String surname,
                         int age,
                         long height,
                         long weight,
                         String password,
                         String card_number,
                         String expiration_date,
                         String CVV,
                         String bank_name,
                         Role role) {

        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);

        User user = new User(username,name,surname,age,height,weight,passwordEncoder.encode(password),card_number,expiration_date,CVV,bank_name,role);


        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(username));
    }
}

package com.example.trening.config;

import com.example.trening.service.TrainerService;
import com.example.trening.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

@Component
public class CustomUsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;

    private final TrainerService trainerService;
    private final BCryptPasswordEncoder passwordEncoder;


    public CustomUsernamePasswordAuthenticationProvider(UserService userService, TrainerService trainerService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.trainerService = trainerService;
        this.passwordEncoder = passwordEncoder;
    }




    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if ("".equals(username) || "".equals(password)) {
            throw new BadCredentialsException("Invalid Credentials");
        }

        UserDetails userDetails = this.trainerService.loadUserByUsername(username);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Password is incorrect!");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}

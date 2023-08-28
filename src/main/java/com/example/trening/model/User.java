package com.example.trening.model;

import com.example.trening.model.enumeration.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@Entity
@Table(name = "client")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String name;
    private String surname;
    private int age;
    private long height;
    private long weight;
    private String password;

    private String card_number;

    private String expiration_date;

    private String CVV;

    private String bank_name;



    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User(String username, String name, String surname, int age, long height, long weight, String password, String card_number, String expiration_date, String CVV, String bank_name, Role role) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.password = password;
        this.card_number = card_number;
        this.expiration_date = expiration_date;
        this.CVV = CVV;
        this.bank_name = bank_name;
        this.role = role;
    }

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

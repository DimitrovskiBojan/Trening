package com.example.trening.model;

import com.example.trening.model.enumeration.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
public class Trainer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;

    private String name;

    private String surname;

    private int age;

    private String password;

    private String card_number;

    private String image;
    private String Expiration_date;

    private String CVV;

    private String bank_name;

    private long height;

    private long weight;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DietPlan> plans;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingTerm> terms;



    @Enumerated(value = EnumType.STRING)
    private Role role;


    public Trainer(String username, String name, String surname, int age, String password, String card_number, String expiration_date, String CVV, String bank_name, String image,Role role) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.password = password;
        this.card_number = card_number;
        Expiration_date = expiration_date;
        this.CVV = CVV;
        this.bank_name = bank_name;
        this.image = image;
        this.role = role;
    }

    public Trainer(String username, String name, String surname, int age, String password, long height, long weight, String card_number, String expiration_date, String CVV, String bank_name,Role role) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.password = password;
        this.card_number = card_number;
        Expiration_date = expiration_date;
        this.CVV = CVV;
        this.bank_name = bank_name;

        this.weight = weight;
        this.height = height;
        this.role = role;
    }

    public Trainer() {
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

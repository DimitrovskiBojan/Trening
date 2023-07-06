package com.example.trening.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Trainer {
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

    private String Expiration_date;

    private String CVV;

    private String bank_name;

    public Trainer(String username, String name, String surname, int age, String password, String card_number, String expiration_date, String CVV, String bank_name) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.password = password;
        this.card_number = card_number;
        Expiration_date = expiration_date;
        this.CVV = CVV;
        this.bank_name = bank_name;
    }

    public Trainer() {
    }
}

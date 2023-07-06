package com.example.trening.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "client")
public class User {
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

    public User(String username, String name, String surname, int age, long height, long weight, String password, String card_number, String expiration_date, String CVV, String bank_name) {
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
    }

    public User() {
    }
}

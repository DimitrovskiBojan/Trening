package com.example.trening.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "dietplan")
public class DietPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String cena;

    public DietPlan() {
    }

    public DietPlan(String cena) {
        this.cena = cena;
    }
}

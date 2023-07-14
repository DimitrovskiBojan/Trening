package com.example.trening.model;

import jakarta.persistence.*;
import lombok.Data;

import java.awt.*;


@Data
@Entity
@Table(name = "dietplan")
public class DietPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long price;

    private String type;

    @ManyToOne
    private Trainer created_by;

    private long rate;


    private String description;

    private String content;


    public DietPlan() {
    }

    public DietPlan(Long price, String type, Trainer created_by, String description, String content) {
        this.price = price;
        this.type = type;
        this.created_by = created_by;
        this.content = content;
        this.description = description;
    }
}

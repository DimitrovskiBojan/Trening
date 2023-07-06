package com.example.trening.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

@Data
@Entity
public class TrainingTerm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    private String datum;
    private String start;
    private String end_time;

    private Long price;


    @ManyToOne
    private Trainer trainer;

    public TrainingTerm(String datum, String start, String end_time, Long price, Trainer trainer) {
        this.datum = datum;
        this.start = start;
        this.end_time = end_time;
        this.price = price;
        this.trainer = trainer;
    }

    public TrainingTerm() {
    }
}

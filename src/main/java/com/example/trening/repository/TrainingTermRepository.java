package com.example.trening.repository;

import com.example.trening.model.TrainingTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingTermRepository extends JpaRepository<TrainingTerm, Long> {
}

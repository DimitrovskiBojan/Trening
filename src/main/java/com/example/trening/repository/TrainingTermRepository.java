package com.example.trening.repository;

import com.example.trening.model.DietPlan;
import com.example.trening.model.Trainer;
import com.example.trening.model.TrainingTerm;
import com.example.trening.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingTermRepository extends JpaRepository<TrainingTerm, Long> {

    List<TrainingTerm> findAllByTrainer_Id(Long id);

    @Query("SELECT dp FROM TrainingTerm dp WHERE dp.trainer = :trainer")
    List<TrainingTerm> findAllByCreated_by(@Param("trainer") Trainer trainer);

    @Query("SELECT dp FROM TrainingTerm dp WHERE dp.trainer = :trainer AND dp.taken = false")
    List<TrainingTerm> findAllByNotTaken(@Param("trainer") Trainer trainer);

    List<TrainingTerm> findAllByTrainer_IdAndTakenFalse(Long id);

}

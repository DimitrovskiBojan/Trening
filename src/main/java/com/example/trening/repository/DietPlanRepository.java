package com.example.trening.repository;

import com.example.trening.model.DietPlan;
import com.example.trening.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DietPlanRepository extends JpaRepository<DietPlan, Long> {

    @Query("SELECT dp FROM DietPlan dp WHERE dp.created_by = :trainer")
    List<DietPlan> findAllByCreated_by(@Param("trainer") Trainer trainer);

}

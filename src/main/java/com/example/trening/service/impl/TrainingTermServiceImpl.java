package com.example.trening.service.impl;


import com.example.trening.model.DietPlan;
import com.example.trening.model.Trainer;
import com.example.trening.model.TrainingTerm;
import com.example.trening.model.exceptions.DietPlanAlreadyBought;
import com.example.trening.model.exceptions.DietPlanNotFoundException;
import com.example.trening.model.exceptions.TrainerNotFoundException;
import com.example.trening.model.exceptions.TrainingTermNotFoundException;
import com.example.trening.repository.TrainerRepository;
import com.example.trening.repository.TrainingTermRepository;
import com.example.trening.service.TrainingTermService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingTermServiceImpl implements TrainingTermService {

    private final TrainingTermRepository trainingTermRepository;
    private final TrainerRepository trainerRepository;

    public TrainingTermServiceImpl(TrainingTermRepository trainingTermRepository, TrainerRepository trainerRepository) {
        this.trainingTermRepository = trainingTermRepository;
        this.trainerRepository = trainerRepository;
    }


    @Override
    public List<TrainingTerm> findAll() {
        return this.trainingTermRepository.findAll();
    }

    @Override
    public Optional<TrainingTerm> findById(Long id) {
        return this.trainingTermRepository.findById(id);
    }

    @Override
    public Optional<TrainingTerm> save(String datum, String start, String end_time, Long price, Long  trainer) {

        Trainer trainer1 = this.trainerRepository.findById(trainer).orElseThrow(()-> new TrainerNotFoundException(trainer));

        TrainingTerm trainingTerm = new TrainingTerm(datum,start,end_time,price,trainer1);
        this.trainingTermRepository.save(trainingTerm);
        return Optional.of(trainingTerm);
    }

    @Override
    public Optional<TrainingTerm> edit(Long id, String datum, String start, String end_time, Long price, Long trainer) {

        TrainingTerm trainingTerm = this.trainingTermRepository.findById(id).orElseThrow(() -> new TrainingTermNotFoundException(id));

        Trainer trainer1 = this.trainerRepository.findById(trainer).orElseThrow(()-> new TrainerNotFoundException(trainer));


        trainingTerm.setDatum(datum);
        trainingTerm.setStart(start);
        trainingTerm.setEnd_time(end_time);
        trainingTerm.setTrainer(trainer1);
        trainingTerm.setPrice(price);

        this.trainingTermRepository.save(trainingTerm);

        return Optional.of(trainingTerm);
    }

    @Override
    public List<TrainingTerm> findAllByTrainers(Long id) {
        return this.trainingTermRepository.findAllByTrainer_IdAndTakenFalse(id);
    }

    @Override
    public List<TrainingTerm> findAllByCreated_by(Trainer trainer) {
        return this.trainingTermRepository.findAllByCreated_by(trainer);
    }

    @Override
    public List<TrainingTerm> findAllByNotTaken(Trainer trainer) {
        return this.trainingTermRepository.findAllByNotTaken(trainer);
    }

    @Override
    public void addTermToClient(Long trainerId, Long trainingTermId) {
        Trainer trainer = this.trainerRepository.findById(trainerId).orElseThrow(()->new TrainerNotFoundException(trainerId));

        TrainingTerm trainingTerm = this.trainingTermRepository.findById(trainingTermId).orElseThrow(() -> new DietPlanNotFoundException(trainingTermId));

        trainer.getTerms().add(trainingTerm);

        this.trainerRepository.save(trainer);

    }

    @Override
    public void removeTermFromClient(Long trainerId, Long termId) {
        Trainer trainer = this.trainerRepository.findById(trainerId).orElseThrow(()->new TrainerNotFoundException(trainerId));

        TrainingTerm trainingTerm = this.trainingTermRepository.findById(termId).orElseThrow(() -> new DietPlanNotFoundException(termId));

        TrainingTerm trainingTerm1 = trainingTerm;

        trainer.getTerms().remove(trainingTerm);


        this.trainerRepository.save(trainer);

        trainingTerm1.setTaken(false);
        this.trainingTermRepository.save(trainingTerm1);


    }

    @Override
    public List<TrainingTerm> findAllTermsForTrainer(Long trainerId) {

        Trainer trainer = this.trainerRepository.findById(trainerId).orElseThrow(()->new TrainerNotFoundException(trainerId));

        return trainer.getTerms();
    }

    @Override
    public void deleteById(Long id) {
        this.trainingTermRepository.deleteById(id);
    }

    @Override
    public void take(Long id) {

        TrainingTerm trainingTerm = this.trainingTermRepository.findById(id).orElseThrow(() -> new DietPlanNotFoundException(id));

        trainingTerm.setTaken(true);

        this.trainingTermRepository.save(trainingTerm);

    }

    @Override
    public void untake(Long id) {

        TrainingTerm trainingTerm = this.trainingTermRepository.findById(id).orElseThrow(() -> new TrainingTermNotFoundException(id));

        trainingTerm.setTaken(false);

        this.trainingTermRepository.save(trainingTerm);
    }

}

package com.example.gymcrmcore.service;

import com.example.gymcrmcore.entity.Trainee;
import com.example.gymcrmcore.entity.Trainer;
import com.example.gymcrmcore.entity.Training;
import com.example.gymcrmcore.entity.TrainingType;
import com.example.gymcrmcore.repository.TrainingRepository;
import com.example.gymcrmcore.repository.TrainingTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;

    private final TrainingTypeRepository trainingTypeRepository;

    private final TraineeService traineeService;

    private final TrainerService trainerService;

    public TrainingService(TrainingRepository trainingRepository, TrainingTypeRepository trainingTypeRepository, TraineeService traineeService, TrainerService trainerService) {
        this.trainingRepository = trainingRepository;
        this.trainingTypeRepository = trainingTypeRepository;
        this.traineeService = traineeService;
        this.trainerService = trainerService;
    }


    public List<Training> getAllTraining() {
        return trainingRepository.findAll();

    }

    public Training getTrainingById(Long id) {
        Optional<Training> trainerOptional = trainingRepository.findById(id);
        return trainerOptional.orElse(null);
    }


    public Training createTraining(Training training) {

        TrainingType specialization = trainingTypeRepository.findById(training.getTrainingType().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid specialization ID"));

        Long traineeId = training.getTrainee().getId();
        Optional<Trainee> traineeOptional = traineeService.getTraineeById(traineeId);
        Trainee trainee = traineeOptional.orElseThrow(() -> new IllegalArgumentException("Trainee not found"));


        Long trainerId = training.getTrainer().getId();
        Optional<Trainer> trainerOptional = trainerService.getTrainerById(trainerId);
        Trainer trainer = trainerOptional.orElseThrow(() -> new IllegalArgumentException("Trainer not found"));

        training.setTrainee(trainee);
        training.setTrainer(trainer);
        training.setTrainingType(specialization);

        Training savedTraining = trainingRepository.save(training);
        return savedTraining;
    }


    public Training updateTraining(Long id, Training training) {
        Optional<Training> trainerOptional = trainingRepository.findById(id);
        if (trainerOptional.isPresent()) {
            training.setId(id); // Set ID for the existing entity
            Training updatedTrainer = trainingRepository.save(training);
            return updatedTrainer;
        }
        return null; // Handle this case in your controller layer as per your requirement
    }

    public void deleteTrainer(Long id) {
        if (trainingRepository.existsById(id)) {
            trainingRepository.deleteById(id);
        }
        // No need to throw an exception if the trainer does not exist
    }


}



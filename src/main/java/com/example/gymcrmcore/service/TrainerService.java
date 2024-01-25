package com.example.gymcrmcore.service;

import com.example.gymcrmcore.entity.Trainer;
import com.example.gymcrmcore.entity.TrainingType;
import com.example.gymcrmcore.repository.TrainerRepository;
import com.example.gymcrmcore.repository.TrainingTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TrainerService {

    private TrainerRepository trainerRepository;
    private TrainingTypeRepository trainingTypeRepository;

    @Autowired
    public void setTrainerRepository(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Autowired
    public void setTrainingTypeRepository(TrainingTypeRepository trainingTypeRepository) {
        this.trainingTypeRepository = trainingTypeRepository;
    }

    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    public Optional<Trainer> getTrainerById(Long id) {
        return trainerRepository.findById(id);
    }

    public Trainer saveTrainer(Trainer trainer) {
        trainer.setUsername(calculateUsername(trainer.getFirstName(), trainer.getLastName()));
        trainer.setPassword(generatePassword());
        trainer.setIsActive(true);
        setSpecialization(trainer);
        return trainerRepository.save(trainer);
    }

    public Trainer updateTrainer(Long id, Trainer updatedTrainer) {
        Optional<Trainer> existingTrainerOptional = trainerRepository.findById(id);

        if (existingTrainerOptional.isPresent()) {
            Trainer existingTrainer = existingTrainerOptional.get();
            existingTrainer.setFirstName(updatedTrainer.getFirstName());
            existingTrainer.setLastName(updatedTrainer.getLastName());
            existingTrainer.setUsername(updatedTrainer.getUsername());
            existingTrainer.setPassword(updatedTrainer.getPassword());
            existingTrainer.setIsActive(updatedTrainer.getIsActive());
            existingTrainer.setSpecialization(updatedTrainer.getSpecialization());

            return trainerRepository.save(existingTrainer);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainer not found");
        }
    }

    public void deleteTrainer(Long id) {
        trainerRepository.deleteById(id);
    }

    private String calculateUsername(String firstName, String lastName) {
        String baseUsername = firstName + "." + lastName;
        String calculatedUsername = baseUsername;
        int counter = 0;

        while (trainerRepository.existsByUsername(calculatedUsername)) {
            calculatedUsername = baseUsername + counter++;
        }
        return calculatedUsername;
    }

    private String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    private void setSpecialization(Trainer trainer) {
        String specializationName = trainer.getSpecialization().getTrainingTypeName();

        Optional<TrainingType> existingSpecialization = trainingTypeRepository.findByTrainingTypeName(specializationName);

        if (existingSpecialization.isPresent()) {
            trainer.setSpecialization(existingSpecialization.get());
        } else {
            TrainingType newSpecialization = new TrainingType();
            newSpecialization.setTrainingTypeName(specializationName);
            TrainingType savedSpecialization = trainingTypeRepository.save(newSpecialization);
            trainer.setSpecialization(savedSpecialization);
        }
    }
}

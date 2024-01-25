package com.example.gymcrmcore.controller;

import com.example.gymcrmcore.entity.Training;
import com.example.gymcrmcore.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/trainings")
public class TrainingController {

    private final TrainingService trainingService;

    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping
    public ResponseEntity<Training> createTraining(@RequestBody Training trainingDTO) {
        Training newTraining = trainingService.createTraining(trainingDTO);
        return new ResponseEntity<>(newTraining, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Training>> getAllTraining() {
        List<Training> training = trainingService.getAllTraining();
        return new ResponseEntity<>(training, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Training> getTrainingById(@PathVariable Long id) {
        Training training = trainingService.getTrainingById(id);
        return new ResponseEntity<>(training, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Training> updateTraining(@PathVariable Long id, @RequestBody Training training) {
        Training updatedTrainer = trainingService.updateTraining(id, training);

        return new ResponseEntity<>(updatedTrainer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTraining(@PathVariable Long id) {
        trainingService.deleteTrainer(id);
        return ResponseEntity.noContent().build();
    }


}


package com.example.GymCRM.controller;

import com.example.GymCRM.dto.TrainingDTO;
import com.example.GymCRM.service.interfaces.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/trainings")
public class TrainingController {

    private final TrainingService trainingService;

    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping
    public ResponseEntity<TrainingDTO> createTraining(@RequestBody TrainingDTO trainingDTO) {
        TrainingDTO newTraining = trainingService.createTraining(trainingDTO);
        return new ResponseEntity<>(newTraining, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TrainingDTO>> getAllTraining() {
        List<TrainingDTO> trainingDTOS = trainingService.getAllTraining();
        return new ResponseEntity<>(trainingDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingDTO> getTrainingById(@PathVariable Long id) {
        TrainingDTO training = trainingService.getTrainingById(id);
        return new ResponseEntity<>(training, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingDTO> updateTraining(@PathVariable Long id, @RequestBody TrainingDTO trainingDTO) {
        TrainingDTO updatedTrainer = trainingService.updateTraining(id, trainingDTO);

        return new ResponseEntity<>(updatedTrainer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTraining(@PathVariable Long id) {
        trainingService.deleteTrainer(id);
        return ResponseEntity.noContent().build();
    }


}


package com.example.gymcrmcore.controller;

import com.example.gymcrmcore.entity.Trainee;
import com.example.gymcrmcore.service.TraineeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trainees")
@Slf4j
public class TraineeController {

    private final TraineeService traineeService;

    @Autowired
    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @GetMapping
    public List<Trainee> getAllTrainees() {
        log.info("Endpoint: GET /trainees");
        return traineeService.getAllTrainees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainee> getTraineeById(@PathVariable Long id) {
        log.info("Endpoint: GET /trainees/{}", id);
        Optional<Trainee> trainee = traineeService.getTraineeById(id);
        return trainee.map(value -> {
            log.info("Trainee with ID {} found", id);
            return new ResponseEntity<>(value, HttpStatus.OK);
        }).orElseGet(() -> {
            log.warn("Trainee with ID {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        });
    }

    @PostMapping
    public ResponseEntity<Trainee> saveTrainee(@RequestBody Trainee trainee) {
        log.info("Endpoint: POST /trainees");
        Trainee savedTrainee = traineeService.saveTrainee(trainee);
        log.info("Trainee saved successfully: {}", savedTrainee);
        return new ResponseEntity<>(savedTrainee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trainee> updateTrainee(@PathVariable Long id, @RequestBody Trainee updatedTrainee) {
        log.info("Endpoint: PUT /trainees/{}", id);
        try {
            Trainee updated = traineeService.updateTrainee(id, updatedTrainee);
            log.info("Trainee with ID {} updated successfully", id);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            log.warn("Trainee with ID {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainee(@PathVariable Long id) {
        traineeService.deleteTrainee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

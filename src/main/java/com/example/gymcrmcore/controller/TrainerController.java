package com.example.gymcrmcore.controller;

import com.example.gymcrmcore.entity.Trainer;
import com.example.gymcrmcore.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping
    public List<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable Long id) {
        Optional<Trainer> trainer = trainerService.getTrainerById(id);
        return trainer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Trainer> saveTrainer(@RequestBody Trainer trainer) {
        Trainer savedTrainer = trainerService.saveTrainer(trainer);
        return new ResponseEntity<>(savedTrainer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trainer> updateTrainer(@PathVariable Long id, @RequestBody Trainer updatedTrainer) {
        try {
            Trainer updated = trainerService.updateTrainer(id, updatedTrainer);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.example.gymcrmcore.controller;

import com.example.gymcrmcore.entity.TrainingType;
import com.example.gymcrmcore.service.TrainingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/training-types")
public class TrainingTypeController {

    private final TrainingTypeService trainingTypeService;

    @Autowired
    public TrainingTypeController(TrainingTypeService trainingTypeService) {
        this.trainingTypeService = trainingTypeService;
    }

    @GetMapping
    public List<TrainingType> getAllTrainingTypes() {
        return trainingTypeService.getAllTrainingTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingType> getTrainingTypeById(@PathVariable Long id) {
        Optional<TrainingType> trainingType = trainingTypeService.getTrainingTypeById(id);
        return trainingType.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<TrainingType> saveTrainingType(@RequestBody TrainingType trainingType) {
        TrainingType savedTrainingType = trainingTypeService.saveTrainingType(trainingType);
        return new ResponseEntity<>(savedTrainingType, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingType> updateTrainingType(@PathVariable Long id, @RequestBody TrainingType updatedTrainingType) {
        try {
            TrainingType updated = trainingTypeService.updateTrainingType(id, updatedTrainingType);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingType(@PathVariable Long id) {
        trainingTypeService.deleteTrainingType(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

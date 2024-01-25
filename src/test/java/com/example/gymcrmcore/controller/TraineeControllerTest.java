package com.example.gymcrmcore.controller;

import com.example.gymcrmcore.controller.TraineeController;
import com.example.gymcrmcore.entity.Trainee;
import com.example.gymcrmcore.service.TraineeService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TraineeControllerTest {

    @Mock
    private TraineeService traineeService;

    @InjectMocks
    private TraineeController traineeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTrainees() {
        // Arrange
        List<Trainee> traineeList = new ArrayList<>();
        when(traineeService.getAllTrainees()).thenReturn(traineeList);

        // Act
        List<Trainee> result = traineeController.getAllTrainees();

        // Assert
        assertNotNull(result);
        assertEquals(traineeList, result);
    }

    @Test
    void testGetTraineeById() {
        // Arrange
        Long traineeId = 1L;
        Trainee trainee = new Trainee();
        when(traineeService.getTraineeById(traineeId)).thenReturn(Optional.of(trainee));

        // Act
        ResponseEntity<Trainee> result = traineeController.getTraineeById(traineeId);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(trainee, result.getBody());
    }

    @Test
    void testGetTraineeByIdNotFound() {
        // Arrange
        Long traineeId = 1L;
        when(traineeService.getTraineeById(traineeId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Trainee> result = traineeController.getTraineeById(traineeId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    void testSaveTrainee() {
        // Arrange
        Trainee traineeToSave = new Trainee();
        when(traineeService.saveTrainee(any())).thenReturn(traineeToSave);

        // Act
        ResponseEntity<Trainee> result = traineeController.saveTrainee(traineeToSave);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(traineeToSave, result.getBody());
    }

    @Test
    void testUpdateTrainee() {
        // Arrange
        Long traineeId = 1L;
        Trainee updatedTrainee = new Trainee();
        when(traineeService.updateTrainee(traineeId, updatedTrainee)).thenReturn(updatedTrainee);

        // Act
        ResponseEntity<Trainee> result = traineeController.updateTrainee(traineeId, updatedTrainee);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updatedTrainee, result.getBody());
    }

    @Test
    void testUpdateTraineeNotFound() {
        // Arrange
        Long traineeId = 1L;
        Trainee updatedTrainee = new Trainee();
        when(traineeService.updateTrainee(traineeId, updatedTrainee)).thenThrow(EntityNotFoundException.class);

        // Act
        ResponseEntity<Trainee> result = traineeController.updateTrainee(traineeId, updatedTrainee);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    void testDeleteTrainee() {
        // Arrange
        Long traineeId = 1L;

        // Act
        ResponseEntity<Void> result = traineeController.deleteTrainee(traineeId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        assertNull(result.getBody());

        // Verify that the service method was called
        verify(traineeService, times(1)).deleteTrainee(traineeId);
    }

    // Add more test methods for other functionalities as needed
}

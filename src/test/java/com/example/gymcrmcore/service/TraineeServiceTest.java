package com.example.gymcrmcore.service;

import com.example.gymcrmcore.entity.Trainee;
import com.example.gymcrmcore.repository.TraineeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TraineeServiceTest {

    @Mock
    private TraineeRepository traineeRepository;

    @InjectMocks
    private TraineeService traineeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTrainees() {
        // Arrange
        List<Trainee> traineeList = new ArrayList<>();
        when(traineeRepository.findAll()).thenReturn(traineeList);

        // Act
        List<Trainee> result = traineeService.getAllTrainees();

        // Assert
        assertNotNull(result);
        assertEquals(traineeList, result);
    }

    @Test
    void testGetTraineeById() {
        // Arrange
        Long traineeId = 1L;
        Trainee trainee = new Trainee();
        when(traineeRepository.findById(traineeId)).thenReturn(Optional.of(trainee));

        // Act
        Optional<Trainee> result = traineeService.getTraineeById(traineeId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(trainee, result.get());
    }


    @Test
    void testSaveTrainee() {
        // Arrange
        Trainee traineeToSave = new Trainee();
        when(traineeRepository.save(any())).thenReturn(traineeToSave);

        // Act
        Trainee result = traineeService.saveTrainee(traineeToSave);

        // Assert
        assertNotNull(result);
        assertEquals(traineeToSave, result);
    }

    @Test
    void testUpdateTrainee() {
        // Arrange
        Long traineeId = 1L;
        Trainee existingTrainee = new Trainee();
        Trainee updatedTrainee = new Trainee();
        when(traineeRepository.findById(traineeId)).thenReturn(Optional.of(existingTrainee));
        when(traineeRepository.save(any())).thenReturn(updatedTrainee);

        // Act
        Trainee result = traineeService.updateTrainee(traineeId, updatedTrainee);

        // Assert
        assertNotNull(result);
        assertEquals(updatedTrainee, result);
    }

    @Test
    void testUpdateTraineeNotFound() {
        // Arrange
        Long traineeId = 1L;
        Trainee updatedTrainee = new Trainee();
        when(traineeRepository.findById(traineeId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> traineeService.updateTrainee(traineeId, updatedTrainee));
    }

    @Test
    void testDeleteTrainee() {
        // Arrange
        Long traineeId = 1L;

        // Act
        traineeService.deleteTrainee(traineeId);

        // Assert
        verify(traineeRepository, times(1)).deleteById(traineeId);
    }

    // Add more test methods for other functionalities as needed
}

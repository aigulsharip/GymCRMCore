package com.example.gymcrmcore.entity;

import com.example.gymcrmcore.entity.Trainee;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class TraineeTest {

    @Test
    void testTraineeConstructor() {
        // Arrange
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String username = "john.doe";
        String password = "password";
        Boolean isActive = true;
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String address = "123 Main St";

        // Act
        Trainee trainee = new Trainee(id, firstName, lastName, username, password, isActive, dateOfBirth, address);

        // Assert
        assertEquals(id, trainee.getId());
        assertEquals(firstName, trainee.getFirstName());
        assertEquals(lastName, trainee.getLastName());
        assertEquals(username, trainee.getUsername());
        assertEquals(password, trainee.getPassword());
        assertEquals(isActive, trainee.getIsActive());
        assertEquals(dateOfBirth, trainee.getDateOfBirth());
        assertEquals(address, trainee.getAddress());
    }

    @Test
    void testTraineeSettersAndGetters() {
        // Arrange
        Trainee trainee = new Trainee();

        // Act
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String username = "john.doe";
        String password = "password";
        Boolean isActive = true;
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String address = "123 Main St";

        trainee.setId(id);
        trainee.setFirstName(firstName);
        trainee.setLastName(lastName);
        trainee.setUsername(username);
        trainee.setPassword(password);
        trainee.setIsActive(isActive);
        trainee.setDateOfBirth(dateOfBirth);
        trainee.setAddress(address);

        // Assert
        assertEquals(id, trainee.getId());
        assertEquals(firstName, trainee.getFirstName());
        assertEquals(lastName, trainee.getLastName());
        assertEquals(username, trainee.getUsername());
        assertEquals(password, trainee.getPassword());
        assertEquals(isActive, trainee.getIsActive());
        assertEquals(dateOfBirth, trainee.getDateOfBirth());
        assertEquals(address, trainee.getAddress());
    }

    // Add more test methods as needed
}

# Gym CRM System

## Overview

This project is a Spring-based module designed to handle a Gym CRM (Customer Relationship Management) system. The system includes functionality for managing trainee profiles, trainer profiles, and training sessions.

## Database Schema

The database schema for the Gym CRM system is designed to accommodate the following structure:

![img.png](img.png)

Please refer to the attached schema image for a visual representation of the database structure.

## Modules

### Trainee Service

The Trainee Service class provides functionality to manage trainee profiles. This includes the ability to create, update, delete, and select trainee profiles.

### Trainer Service
The Trainer Service class is responsible for managing trainer profiles. It offers functionality to create, update, and select trainer profiles.

### Training Service
The Training Service class supports the creation and selection of training profiles. It facilitates the management of training sessions within the Gym CRM system.

## Getting Started

To integrate this module into your project, follow these steps:

1. Clone the repository: `git clone https://github.com/aigulsharip/GymCRMCore.git`
2. Import the project into your preferred IDE.
3. Configure the database connection in the `application.properties` file.
4. Build and run the application.

## Dependencies

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [H2 Database](https://www.h2database.com/html/main.html) (for development/testing)
- Add any additional dependencies...


## Comment of revision of project
The project can be accessed via the following link: https://github.com/aigulsharip/GymCRMCore.git. 
Additional details regarding endpoints and Postman queries can be found in the README file and the Postman queries file.

Based on our discussions and your recommendations, I've implemented the following modifications to the project:

1. The creation of trainee and trainer has been updated to no longer depend on the User entity.
2. All DTO classes have been removed from the project.
3. The logic for adding a training type during the creation of a trainer has been revised. Now, when creating a trainer, we provide the training type name. If the training type already exists in the database, it is linked. If the intended training type does not exist, a new one is created and associated with the trainer.
4. As an illustration, setter-based injection has been introduced in the TrainerService class. In all other service classes, constructor-based injections have been maintained, and @Autowired injections are used in all other controllers.
5. Unit tests have been added for the Trainee, TraineeService, and TraineeController classes. These tests cover 100% of the code in their respective classes, as detailed in the README file.
   ![img.png](img.png)
6. Code logging using @Slf4j has been implemented for the TraineeService and TraineeController classes.

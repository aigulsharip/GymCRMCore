package com.example.GymCRM.service.impl;

import com.example.GymCRM.dto.TraineeDTO;
import com.example.GymCRM.dto.TrainerDTO;
import com.example.GymCRM.dto.TrainingDTO;
import com.example.GymCRM.entity.Training;
import com.example.GymCRM.entity.TrainingType;
import com.example.GymCRM.mapper.TraineeMapper;
import com.example.GymCRM.mapper.TrainerMapper;
import com.example.GymCRM.mapper.TrainingMapper;
import com.example.GymCRM.repository.TrainingRepository;
import com.example.GymCRM.repository.TrainingTypeRepository;
import com.example.GymCRM.service.interfaces.TraineeService;
import com.example.GymCRM.service.interfaces.TrainerService;
import com.example.GymCRM.service.interfaces.TrainingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;

    private final TrainingTypeRepository trainingTypeRepository;

    private final TraineeService traineeService;

    private final TrainerService trainerService;

    private final TrainerMapper trainerMapper;

    private final TraineeMapper traineeMapper;

    private final TrainingMapper trainingMapper;

    public TrainingServiceImpl(TrainingRepository trainingRepository, TrainingTypeRepository trainingTypeRepository, TraineeService traineeService, TrainerService trainerService, TrainerMapper trainerMapper, TraineeMapper traineeMapper, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.trainingTypeRepository = trainingTypeRepository;
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainerMapper = trainerMapper;
        this.traineeMapper = traineeMapper;
        this.trainingMapper = trainingMapper;
    }


    public List<TrainingDTO> getAllTraining() {
        List<Training> trainers = trainingRepository.findAll();
        return trainingMapper.toDtoList(trainers);
    }

    public TrainingDTO getTrainingById(Long id) {
        Optional<Training> trainerOptional = trainingRepository.findById(id);
        return trainerOptional.map(trainingMapper::toDTO).orElse(null);
    }


    public TrainingDTO createTraining(TrainingDTO trainingDTO) {

        TrainingType specialization = trainingTypeRepository.findById(trainingDTO.getTrainingType().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid specialization ID"));

        Long traineeId = trainingDTO.getTrainee().getId();
        Optional<TraineeDTO> traineeOptional = traineeService.getTraineeByIdOptional(traineeId);
        TraineeDTO traineeDTO = traineeOptional.orElseThrow(() -> new IllegalArgumentException("Trainee not found"));


        Long trainerId = trainingDTO.getTrainer().getId();
        Optional<TrainerDTO> trainerOptional = trainerService.getTrainerByIdOptional(trainerId);
        TrainerDTO trainerDTO = trainerOptional.orElseThrow(() -> new IllegalArgumentException("Trainer not found"));

        Training training = trainingMapper.toEntity(trainingDTO);
        training.setTrainee(traineeMapper.traineeDTOToTrainee(traineeDTO));
        training.setTrainer(trainerMapper.toEntity(trainerDTO));
        training.setTrainingType(specialization);

        Training savedTraining = trainingRepository.save(training);
        return trainingMapper.toDTO(savedTraining);
    }


    public TrainingDTO updateTraining(Long id, TrainingDTO trainingDTO) {
        Optional<Training> trainerOptional = trainingRepository.findById(id);
        if (trainerOptional.isPresent()) {
            Training trainerToUpdate = trainingMapper.toEntity(trainingDTO);
            trainerToUpdate.setId(id); // Set ID for the existing entity
            Training updatedTrainer = trainingRepository.save(trainerToUpdate);
            return trainingMapper.toDTO(updatedTrainer);
        }
        return null; // Handle this case in your controller layer as per your requirement
    }

    public void deleteTrainer(Long id) {
        if (trainingRepository.existsById(id)) {
            trainingRepository.deleteById(id);
        }
        // No need to throw an exception if the trainer does not exist
    }


}



package com.example.gymcrmcore.service;

import com.example.GymCRM.dto.TrainingTypeDTO;
import com.example.GymCRM.entity.TrainingType;
import com.example.GymCRM.mapper.TrainingTypeMapper;
import com.example.GymCRM.service.interfaces.TrainingTypeService;
import com.example.gymcrmcore.repository.TrainingTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TrainingTypeServiceImpl implements TrainingTypeService {

    private final TrainingTypeRepository trainingTypeRepository;

    private final TrainingTypeMapper trainingTypeMapper;

    public TrainingTypeServiceImpl(TrainingTypeRepository trainingTypeRepository, TrainingTypeMapper trainingTypeMapper) {
        this.trainingTypeRepository = trainingTypeRepository;
        this.trainingTypeMapper = trainingTypeMapper;
    }

    public TrainingTypeDTO save(TrainingTypeDTO trainingTypeDTO) {
        TrainingType trainingType = trainingTypeMapper.toEntity(trainingTypeDTO);
        return trainingTypeMapper.toDto(trainingTypeRepository.save(trainingType));
    }

    public Optional<TrainingTypeDTO> findById(Long id) {
        return trainingTypeRepository.findById(id).map(trainingTypeMapper::toDto);
    }

    public List<TrainingTypeDTO> findAll() {
        return trainingTypeRepository.findAll().stream().map(trainingTypeMapper::toDto).collect(Collectors.toList());
    }

    public TrainingTypeDTO updateTrainingType(Long id, TrainingTypeDTO updatedTrainingTypeDTO) {
        Optional<TrainingType> optionalTrainingType = trainingTypeRepository.findById(id);

        if (!optionalTrainingType.isPresent()) {
            return null; // or handle differently, maybe throw a custom exception
        }

        TrainingType existingTrainingType = optionalTrainingType.get();
        existingTrainingType.setTrainingTypeName(updatedTrainingTypeDTO.getTrainingTypeName());

        TrainingType updatedTrainingType = trainingTypeRepository.save(existingTrainingType);
        return trainingTypeMapper.toDto(updatedTrainingType);
    }

    public void deleteById(Long id) {
        trainingTypeRepository.deleteById(id);
    }
}


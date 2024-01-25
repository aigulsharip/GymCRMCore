package com.example.gymcrmcore.repository;

import com.example.gymcrmcore.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}

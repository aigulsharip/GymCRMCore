package com.example.gymcrmcore.repository;


import com.example.gymcrmcore.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    boolean existsByUsername(String username);
}

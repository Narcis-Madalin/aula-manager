package com.myapp.aula_manager.repository;

import com.myapp.aula_manager.model.StudyCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudyCycleRepository extends JpaRepository<StudyCycle, Integer> {

    Optional<StudyCycle> findByCycleName(String cycleName);

    boolean existsByCycleName(String cycleName);
}

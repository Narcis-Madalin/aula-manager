package com.myapp.aula_manager.repository;

import com.myapp.aula_manager.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {

    Optional<Specialization> findBySpecializationName(String specializationName);

    List<Specialization> findByStudyCycle_CycleName(String cycleName);

    boolean existsBySpecializationName(String specializationName);
}

package com.myapp.aula_manager.repository;

import com.myapp.aula_manager.model.StudyCycle;
import com.myapp.aula_manager.model.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyGroupRepository extends JpaRepository<StudyGroup, Long> {

    List<StudyGroup> findBySpecialization_SpecializationId (Integer specializationId);

    List<StudyGroup> findBySpecialization_StudyCycle_CycleName (String cycleName);

    List<StudyGroup> findBySpecialization_StudyCycle_CycleId (Integer cycleId);

    List<StudyGroup> findBySpecialization_StudyCycle (StudyCycle studyCycle);
}

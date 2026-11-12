package com.myapp.aula_manager.repository;

import com.myapp.aula_manager.model.Specialization;
import com.myapp.aula_manager.model.StudyCycle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpecializationRepositoryTest {

    @Autowired
    private SpecializationRepository specializationRepository;

    @Autowired
    private StudyCycleRepository studyCycleRepository;

    private Specialization generateSpecializationExample(String specializationName, String cycleName){

        StudyCycle testCycle = new StudyCycle();

        Specialization testSpecialization = new Specialization();

        testSpecialization.setSpecializationName(specializationName);

        Optional<StudyCycle> foundCycle = studyCycleRepository.findByCycleName(cycleName);

        if(foundCycle.isEmpty()){
            testCycle.setCycleName(cycleName);
            studyCycleRepository.save(testCycle);
            testSpecialization.setStudyCycle(testCycle);
        }
        else {
            testSpecialization.setStudyCycle(foundCycle.get());
        }

        /*
        Alternativa de scriere cod/implementare a functiei:

        Specialization testSpecialization = new Specialization();
        testSpecialization.setSpecializationName(specializationName);

        StudyCycle testCycle = studyCycleRepository
                .findByCycleName(cycleName)
                .orElseGet(() -> {
                    StudyCycle newCycle = new StudyCycle();
                    newCycle.setCycleName(cycleName);
                    return studyCycleRepository.save(newCycle);
                });

        testSpecialization.setStudyCycle(testCycle);


         */

        return testSpecialization;
    }

    @Test
    void findBySpecializationName_shouldReturnSpecialization_whenSpecializationExists(){

        // Arrange

        specializationRepository.save(generateSpecializationExample("Artificial Intelligence", "Masters"));

        // Act

        Optional<Specialization> foundSpecialization = specializationRepository.findBySpecializationName("Artificial Intelligence");

        // Assert

        assertTrue(foundSpecialization.isPresent());
        assertEquals("Artificial Intelligence", foundSpecialization.get().getSpecializationName());

    }

    @Test
    void findBySpecializationName_shouldReturnEmpty_whenSpecializationDoesNotExist(){

        // Act

        Optional<Specialization> foundSpecialization = specializationRepository.findBySpecializationName("Unknown Specialization");

        // Assert

        assertTrue(foundSpecialization.isEmpty());
    }

    @Test
    void findByStudyCycle_CycleName_shouldReturnList_whenCycleNameExists(){
        // Arrange

        specializationRepository.save(generateSpecializationExample("CyberSecurity", "Bachelor"));
        specializationRepository.save(generateSpecializationExample("BioInformatics", "Bachelor"));
        specializationRepository.save(generateSpecializationExample("Engineer Software Robotics", "Bachelor"));
        specializationRepository.save(generateSpecializationExample("Big data", "Bachelor"));

        specializationRepository.save(generateSpecializationExample("Informatica-Aplicata", "Doctorate"));

        // Act

        List<Specialization> foundSpecializations = specializationRepository.findByStudyCycle_CycleName("Bachelor");

        // Assert

        assertEquals(4, foundSpecializations.size());

        // aparent sunt ordonate alfabetic dupa numele specializarii in lista, dunno why
        assertEquals("BioInformatics", foundSpecializations.get(1).getSpecializationName());
        assertEquals("CyberSecurity", foundSpecializations.get(2).getSpecializationName());
    }

    @Test
    void findByStudyCycle_CycleName_shouldReturnEmpty_whenCycleNameDoesNotExist(){

        // Act

        List<Specialization> foundSpecializations = specializationRepository.findByStudyCycle_CycleName("Unknown Cycle");

        // Assert

        assertEquals(0, foundSpecializations.size());
        assertTrue(foundSpecializations.isEmpty());
    }

    @Test
    void existsBySpecializationName_shouldReturnTrue_whenSpecializationNameExists(){

        // Arrange

        specializationRepository.save(generateSpecializationExample("CyberSecurity", "Bachelor"));

        // Act

        boolean result = specializationRepository.existsBySpecializationName("CyberSecurity");

        //Assert

        assertTrue(result);
    }

    @Test
    void existsBySpecializationName_shouldReturnFalse_whenSpecializationNameDoesNotExist(){

        // Act

        boolean result = specializationRepository.existsBySpecializationName("Unknown Specialization");

        // Assert

        assertFalse(result);
    }


}

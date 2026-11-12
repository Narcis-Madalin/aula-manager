package com.myapp.aula_manager.repository;

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
public class StudyCycleRepositoryTest {

    @Autowired
    private StudyCycleRepository studyCycleRepository;

    private StudyCycle generateStudyCycleExample(String cycleName){

        StudyCycle testCycle = new StudyCycle();

        testCycle.setCycleName(cycleName);

        return testCycle;
    }

    @Test
    void findByCycleName_shouldReturnStudyCycle_whenStudyCycleExists(){
        // Arrange

        studyCycleRepository.saveAll(List.of(
                generateStudyCycleExample("Licenta-Cycle"),
                generateStudyCycleExample("Master-Cycle"),
                generateStudyCycleExample("Doctorat-Cycle")
        ));

        // Act

        Optional<StudyCycle> doctorateCycle = studyCycleRepository.findByCycleName("Doctorat-Cycle");
        Optional<StudyCycle> bachelorCycle = studyCycleRepository.findByCycleName("Licenta-Cycle");
        Optional<StudyCycle> masterCycle = studyCycleRepository.findByCycleName("Master-Cycle");

        // Assert

        assertTrue(doctorateCycle.isPresent());
        assertTrue(bachelorCycle.isPresent());
        assertTrue(masterCycle.isPresent());

        assertEquals("Master-Cycle", masterCycle.get().getCycleName());
        assertEquals("Doctorat-Cycle", doctorateCycle.get().getCycleName());
        assertEquals("Licenta-Cycle", bachelorCycle.get().getCycleName());

    }

    @Test
    void findByCycleName_shouldReturnEmpty_whenStudyCycleDoesNotExist(){

        // Act

        Optional<StudyCycle> searchedCycleName = studyCycleRepository.findByCycleName("Unknown");

        // Assert

        assertTrue(searchedCycleName.isEmpty());
    }

    @Test
    void existsByCycleName_shouldReturnTrue_whenStudyCycleExists(){

        // Arrange

        studyCycleRepository.save(generateStudyCycleExample("Master-Cycle"));

        // Act

        boolean result = studyCycleRepository.existsByCycleName("Master-Cycle");

        // Assert

        assertTrue(result);

    }

    @Test
    void existsByCycleName_shouldReturnFalse_whenStudyCycleDoesNotExist(){

        // Act

        boolean result = studyCycleRepository.existsByCycleName("Unknown");

        // Assert

        assertFalse(result);

    }
}

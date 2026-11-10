package com.myapp.aula_manager.repository;

import com.myapp.aula_manager.model.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    void findByEmail_shouldReturnUser_whenEmailExists() {

        // Arrange - pregătim datele pentru test
        AppUser user = new AppUser();

        user.setLastName("Test");
        user.setFirstName("User");
        user.setEmail("repository-test@example.com");
        user.setPassword("test-hash");
        user.setRole("STUDENT");

        appUserRepository.save(user);

        // Act - executăm metoda pe care vrem să o testăm
        Optional<AppUser> result =
                appUserRepository.findByEmail("repository-test@example.com");

        // Assert - verificăm rezultatul
        assertTrue(result.isPresent());
        assertEquals("Test", result.get().getLastName());
        assertEquals("User", result.get().getFirstName());
    }
}


package com.myapp.aula_manager.repository;

import com.myapp.aula_manager.model.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    // Metodă auxiliară pentru crearea unui utilizator de test
    private AppUser createTestUser() {
        AppUser user = new AppUser();

        user.setLastName("Test");
        user.setFirstName("User");
        user.setEmail("repository-test@example.com");
        user.setPassword("test-hash");
        user.setRole("STUDENT");

        return user;
    }

    @Test
    void findByEmail_shouldReturnUser_whenEmailExists() {

        // Arrange
        AppUser user = createTestUser();
        appUserRepository.save(user);

        // Act
        Optional<AppUser> result =
                appUserRepository.findByEmail("repository-test@example.com");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Test", result.get().getLastName());
        assertEquals("User", result.get().getFirstName());
    }

    @Test
    void findByEmail_shouldReturnEmpty_whenEmailDoesNotExist() {

        // Act
        Optional<AppUser> result =
                appUserRepository.findByEmail("some-email@email.com");

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void existsByEmail_shouldReturnTrue_whenEmailExists() {

        // Arrange
        AppUser user = createTestUser();
        appUserRepository.save(user);

        // Act
        boolean result =
                appUserRepository.existsByEmail("repository-test@example.com");

        // Assert
        assertTrue(result);
    }

    @Test
    void existsByEmail_shouldReturnFalse_whenEmailDoesNotExist() {

        // Act
        boolean result =
                appUserRepository.existsByEmail("some-email@email.com");

        // Assert
        assertFalse(result);
    }
}

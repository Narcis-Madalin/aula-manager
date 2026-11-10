package com.myapp.aula_manager.repository;

import com.myapp.aula_manager.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional <AppUser> findByEmail(String email);

    boolean existsByEmail(String email);

    List<AppUser> findByRole(String role);

    long countByRole(String role);
}

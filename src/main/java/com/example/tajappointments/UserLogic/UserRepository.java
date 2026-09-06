package com.example.tajappointments.UserLogic;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    default User getUserById(UUID id) {
        return findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }
}

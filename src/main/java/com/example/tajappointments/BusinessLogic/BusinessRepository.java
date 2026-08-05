package com.example.tajappointments.BusinessLogic;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusinessRepository extends JpaRepository<Business, UUID> {
}

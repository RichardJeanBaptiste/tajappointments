package com.example.tajappointments.ServiceForm;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServicesRepository extends JpaRepository<Services,UUID> {
}

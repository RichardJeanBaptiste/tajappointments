package com.example.tajappointments.AppointmentLogic;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppointmentsRepository extends JpaRepository<Appointments, UUID> {
}

package com.example.tajappointments.AppointmentLogic;

import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.*;

@Service
public class AppointmentService {

    private final AppointmentsRepository appointmentsRepository;

    public AppointmentService(AppointmentsRepository appointmentsRepository) {
        this.appointmentsRepository = appointmentsRepository;
    }

    public Appointments create(Appointments appointments) {
        return appointmentsRepository.save(appointments);
    }

    public Appointments findById(UUID id) {
        return appointmentsRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Appointments Not Found"));
    }

    public void addAppointments(List<Appointments> appointments) {

        appointmentsRepository.saveAll(appointments);
    }

    public void removeAppointments(List<Appointments> A) {
        appointmentsRepository.deleteAll(A);
    }

    public void editAppointments(List<Appointments> x) {
        
        appointmentsRepository.saveAll(x);
    }
}

package com.example.tajappointments.AppointmentLogic;

import org.springframework.stereotype.Service;
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

        List<Appointments> newAppointments = new ArrayList<>();

        newAppointments.addAll(appointments);

        appointmentsRepository.saveAll(newAppointments);
    }
}

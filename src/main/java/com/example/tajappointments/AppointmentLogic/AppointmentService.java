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

    public void editAppointments(UUID appointmentId, HashMap<String, String> editFields) {

        for ( String field : editFields.keySet()) {

            String currentVal = editFields.get(field);

            System.out.println(currentVal);
        }
        
        // UUID serviceId = UUID.fromString(newFields.get(0));
        // Instant date = Instant.parse(newFields.get(1));
        // Instant startTime = Instant.parse(newFields.get(2));
        // Instant endTime = Instant.parse(newFields.get(3));

        // Appointments x = findById(appointmentId);
        // x.setServiceId(serviceId);
        // x.setDate(date);
        // x.setStartTime(startTime);
        // x.setEndTime(endTime);
    }
}

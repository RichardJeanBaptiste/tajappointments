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


        Appointments x = findById(appointmentId);

        for ( String field : editFields.keySet()) {

            String currentVal = editFields.get(field);

            switch(field) {
                case "serviceId":
                    if (!currentVal.isEmpty()) {
                        x.setServiceId(UUID.fromString(currentVal));
                    }
                    break;
                case "date":
                    if(!currentVal.isEmpty()) {
                        x.setDate(Instant.parse(currentVal));
                    }
                    break;
                case "startTime":
                    if(!currentVal.isEmpty()) {
                        x.setStartTime(Instant.parse(currentVal));
                    }
                    break;
                case "endTime":
                    if(!currentVal.isEmpty()) {
                        x.setEndTime(Instant.parse(currentVal));
                    }
                    break;
                default:
                    break;
            }
        }
        
        appointmentsRepository.save(x);
    }
}

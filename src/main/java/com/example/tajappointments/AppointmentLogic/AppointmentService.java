package com.example.tajappointments.AppointmentLogic;


import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private final AppointmentsRepository appointmentsRepository;

    public AppointmentService(AppointmentsRepository appointmentsRepository) {
        this.appointmentsRepository = appointmentsRepository;
    }

    public Appointments create(Appointments appointments) {
        return appointmentsRepository.save(appointments);
    }
}

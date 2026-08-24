package com.example.tajappointments.AppointmentLogic;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.Instant;
import java.util.*;


@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/appointment")
    public Appointments create(@RequestBody Appointments appointment) {
        return appointmentService.create(appointment);
    }

    @PostMapping("/api/add/appointments")
    public String addAppointment(@RequestBody AppointmentForm[] form) {

        List<Appointments> x = new ArrayList<>();
 
        try {

            for (AppointmentForm currentAppointment : form) {

                UUID businessId = UUID.fromString(currentAppointment.getBusinessId());
                UUID clientId = UUID.fromString(currentAppointment.getClientId());
                UUID serviceId = UUID.fromString(currentAppointment.getServiceId());
                Instant date = Instant.parse(currentAppointment.getDate());
                Instant startTime = Instant.parse(currentAppointment.getStartTime());
                Instant endTime = Instant.parse(currentAppointment.getEndTime());

                Appointments newAppointment = new Appointments();

                newAppointment.setDate(date);
                newAppointment.setBusinessId(businessId);
                newAppointment.setClientId(clientId);
                newAppointment.setServiceId(serviceId);
                newAppointment.setStartTime(startTime);
                newAppointment.setEndTime(endTime);

                x.add(newAppointment);
                
            }

            appointmentService.addAppointments(x);

            return "Appointment Added";
            
        } catch (Exception e) {
        
            System.out.println(e);
            return "Failed to create appointment";
        }
        
    }

    @PostMapping("/api/remove/appointments")
    public String removeAppointments(@RequestBody AppointmentForm[] form) {
        
        List<Appointments> remove = new ArrayList<>();

        try {

            for( AppointmentForm x : form) {

                UUID appointmentId = UUID.fromString(x.getAppointmentId());

                Appointments current = appointmentService.findById(appointmentId);

                remove.add(current);
            }

            appointmentService.removeAppointments(remove);
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        
        return "Appointment Form";
    }

    @PostMapping("/api/edit/appointments")
    public String editAppointments(@RequestBody AppointmentForm form) {
        
        UUID appointmentId = UUID.fromString(form.getAppointmentId());
        String serviceId = form.getServiceId();
        String date = form.getDate();
        String startTime = form.getStartTime();
        String endTime = form.getEndTime();

        HashMap<String, String> fields = new HashMap<String, String>(
            Map.of(
                "serviceId" , serviceId,
                "date", date,
                "startTime", startTime,
                "endTime", endTime 
            )
        );

        appointmentService.editAppointments(appointmentId, fields);

        
        return "edit appointments";
    }
    
    

}
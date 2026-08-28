package com.example.tajappointments.AppointmentLogic;

import com.example.tajappointments.AppointmentLogic.AppointmentsRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.Instant;
import java.util.*;


@RestController
public class AppointmentController {

    private final AppointmentsRepository appointmentsRepository;
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService, AppointmentsRepository appointmentsRepository) {
        this.appointmentService = appointmentService;
        this.appointmentsRepository = appointmentsRepository;
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
    public String editAppointments(@RequestBody AppointmentForm[] form) {
        
        // UUID appointmentId = UUID.fromString(form.getAppointmentId());
        // String serviceId = form.getServiceId();
        // String date = form.getDate();
        // String startTime = form.getStartTime();
        // String endTime = form.getEndTime();

        // HashMap<String, String> fields = new HashMap<String, String>(
        //     Map.of(
        //         "serviceId" , serviceId,
        //         "date", date,
        //         "startTime", startTime,
        //         "endTime", endTime 
        //     )
        // );

        // appointmentService.editAppointments(appointmentId, fields);

        List<Appointments> edits = new ArrayList<>();

        UUID appointmentId;
        String serviceId;
        String date;
        String startTime;
        String endTime;

        try {

            for(AppointmentForm x : form) {

                appointmentId = UUID.fromString(x.getAppointmentId());
                serviceId = x.getServiceId();
                date = x.getDate();
                startTime =  x.getStartTime();
                endTime = x.getEndTime();

                Appointments current = appointmentService.findById(appointmentId);

                if (current != null){

                    if(x.getServiceId() != null) {
                        current.setServiceId(UUID.fromString(serviceId));
                    }

                    if(x.getDate() != null) {
                        current.setDate(Instant.parse(date));
                    }

                    if(x.getStartTime() != null) {
                        current.setStartTime(Instant.parse(startTime));
                    }

                    if(x.getEndTime() != null) {
                        current.setEndTime(Instant.parse(endTime));
                    }
                }

                edits.add(current);
            }

            appointmentService.editAppointments(edits);

            return "Appointments Edited";
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            return "Something went wrong adding appointments";
        }

    }
    
    

}
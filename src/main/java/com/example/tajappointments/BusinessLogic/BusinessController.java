package com.example.tajappointments.BusinessLogic;

import com.example.tajappointments.AppointmentLogic.AppointmentForm;
import com.example.tajappointments.AppointmentLogic.AppointmentService;
import com.example.tajappointments.AppointmentLogic.Appointments;
import com.example.tajappointments.ServiceForm.Services;
import com.example.tajappointments.ServiceForm.ServicesForm;
import com.example.tajappointments.ServiceForm.ServicesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.*;

@RestController
public class BusinessController {

    //private static final Logger log = LoggerFactory.getLogger(BusinessController.class);
    private final BusinessService businessService;
    private final ServicesService servicesService;
    private final AppointmentService appointmentService;

    public BusinessController(BusinessService businessService, ServicesService servicesService, AppointmentService appointmentService) {
        this.businessService = businessService;
        this.servicesService = servicesService;
        this.appointmentService = appointmentService;
    }

    @PostMapping("/business")
    public Business create(@RequestBody Business business){
        return businessService.create(business);
    }

    @PostMapping("/appointment")
    public Appointments create(@RequestBody Appointments appointment) {
        return appointmentService.create(appointment);
    }

    @PostMapping("/api/new/business")
    public String newBusinessHandler(@RequestBody BusinessForm form) {

        String businessName = form.getBusinessName();
        String email = form.getBusinessEmail();
        String ownerName = form.getOwnerName();
        String ownerId = form.getOwnerId();

        Business x = new Business();

        x.setName(ownerName);
        x.setEmail(email);
        x.setBusinessName(businessName);
        x.setOwnerId(ownerId);

        businessService.create(x);

        return "new business";
    }

    @PostMapping("/api/edit/business")
    public String editBusiness(@RequestBody BusinessForm form) {

        UUID id = UUID.fromString(form.getBusinessId());
        String name = (form.getOwnerName() == null) ? "" : form.getOwnerName();
        String email = (form.getBusinessEmail() == null) ? "" : form.getBusinessEmail();
        String address = (form.getBusinessAddress() == null) ? "" : form.getBusinessAddress();

        HashMap<String, String> fields = new HashMap<String, String>(
                Map.of(
                "name", name,
                "email", email,
                "address", address
        ));

        businessService.editFields(id, fields);

        return "Business Edited";
    }

    @PostMapping("/api/new/service")
    public String newService(@RequestBody ServicesForm[] form) {

        ArrayList<UUID> serviceIds = new ArrayList<>();
        List<Services> newServices = new ArrayList<>();

        // Test - ID -> get userId from path
        UUID businessId = UUID.fromString("09c695e6-bc71-4ffd-94bd-6f450bf128b5");

        try {

            for(ServicesForm currentService : form){
                Services newService = new Services();

                UUID newId = UUID.randomUUID();

                newService.setId(newId);
                newService.setName(currentService.getServiceName());
                newService.setDescription(currentService.getServiceDescription());
                newService.setDuration(Integer.parseInt(currentService.getServiceDuration()));
                newService.setCost(currentService.getServiceCost());
                newService.setBusinessId(String.valueOf(businessId));

                newServices.add(newService);
                serviceIds.add(newId);
            }

            servicesService.addMultipleServices(newServices);
            businessService.addToServicesById(businessId, serviceIds);

            return "Services Array";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @PostMapping("/api/remove/service")
    public String removeService(@RequestBody ServicesForm[] form) {


        ArrayList<UUID> serviceIds = new ArrayList<>();
        ArrayList<Services> servicesToRemove = new ArrayList<>();
        String businessId = "";

        try {

            for (ServicesForm currentService: form) {

                if(businessId.isEmpty()) {
                    businessId = currentService.getBusinessId();
                }

                Services x = servicesService.findById(UUID.fromString(currentService.getServiceQuery()));

                serviceIds.add(x.getId());
                servicesToRemove.add(x);
            }

            businessService.removeServicesById(UUID.fromString(businessId), serviceIds);
            servicesService.removeServices(servicesToRemove);

            return "Service Removed";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/api/add/appointments")
    public String addAppointment(@RequestBody AppointmentForm form) {

        
        
        try {

            Instant date = Instant.parse(form.getDate());
            UUID businessId = UUID.fromString(form.getBusinessId());
            UUID clientId = UUID.fromString(form.getClientId());
            UUID serviceId = UUID.fromString(form.getServiceId());
            Instant startTime = Instant.parse(form.getStartTime());
            Instant endTime = Instant.parse(form.getEndTime());

            // Services s = servicesService.findById(serviceId);

            Appointments newAppointment = new Appointments();

            newAppointment.setDate(date);
            newAppointment.setBusinessId(businessId);
            newAppointment.setClientId(clientId);
            newAppointment.setServiceId(serviceId);
            newAppointment.setStartTime(startTime);
            newAppointment.setEndTime(endTime);

            appointmentService.create(newAppointment);

            //System.out.println(date + "\n" + businessId + "\n" + clientId + "\n" + serviceId);

            return "Appointment Added";
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            return "Failed to create appointment";
        }
        

        
    }

//    @PostMapping("/api/edit/service")
//    public String editService(@RequestBody Ser) {
//
//
//    }
    
}

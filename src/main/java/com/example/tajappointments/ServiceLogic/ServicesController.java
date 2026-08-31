package com.example.tajappointments.ServiceLogic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tajappointments.BusinessLogic.BusinessService;

@RestController
public class ServicesController {
    

    private final ServicesService servicesService;
    private final BusinessService businessService;

    public ServicesController(ServicesService servicesService, BusinessService businessService) {
        this.servicesService = servicesService;
        this.businessService = businessService;
    }

    @PostMapping("/services")
    public ResponseEntity<Services> createService(@RequestBody Services service) {
        Services createdService = servicesService.create(service);
        return ResponseEntity.status(201).body(createdService);
    }

    @PostMapping("/api/new/service")
    public ResponseEntity<String> newService(@RequestBody ServicesForm[] form) {
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

            return ResponseEntity.ok("Services Array");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/api/remove/service")
    public ResponseEntity<String> removeService(@RequestBody ServicesForm[] form) {

        ArrayList<UUID> serviceIds = new ArrayList<>();
        ArrayList<Services> servicesToRemove = new ArrayList<>();
        String businessId = "";

        try {

            for (ServicesForm currentService: form) {

                System.out.println(currentService.getServiceQuery());

                if(businessId.isEmpty()) {
                    businessId = currentService.getBusinessId();
                }

                System.out.println(businessId);

                Services x = servicesService.findById(UUID.fromString(currentService.getServiceQuery()));

                serviceIds.add(x.getId());
                servicesToRemove.add(x);
            }
            
            System.out.println("Removing services for business: " + businessId);
            businessService.removeServicesById(UUID.fromString(businessId), serviceIds);

            System.out.println("Removing services from service list");
            servicesService.removeServices(servicesToRemove);

            return ResponseEntity.ok("Service Removed");
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } 

    @PostMapping("/api/edit/service")
    public ResponseEntity<String> editService(@RequestBody ServicesForm[] form) {

        ArrayList<Services> servicesToEdit = new ArrayList<>();

        try {

            for(ServicesForm currentService: form) {

                UUID id = UUID.fromString(currentService.getServiceId());
                String name = (currentService.getServiceName() == null) ? "" : currentService.getServiceName();
                String description = (currentService.getServiceDescription() == null) ? "" : currentService.getServiceDescription();
                int duration = (currentService.getServiceDuration() == null) ? 0 : Integer.parseInt(currentService.getServiceDuration());
                double cost = (currentService.getServiceCost() == null) ? 0.0 : Double.parseDouble(currentService.getServiceCost());

                Services current = servicesService.findById(id);

                if( current != null ) {

                    if(name != null && !name.isEmpty()) {
                        current.setName(name);
                    }

                    if(description != null && !description.isEmpty()) {
                        current.setDescription(description);
                    }

                    if(duration != 0) {
                        current.setDuration(duration);
                    }

                    if(cost != 0.0) {
                        current.setCost(String.valueOf(cost));
                    }
                }

                servicesToEdit.add(current);
               
            }

            servicesService.addMultipleServices(servicesToEdit);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Error editing service");
        }

        return ResponseEntity.ok("Service Edited");
    }

}

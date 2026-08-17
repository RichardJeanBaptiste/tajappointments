package com.example.tajappointments.BusinessLogic;

import com.example.tajappointments.ServiceForm.Services;
import com.example.tajappointments.ServiceForm.ServicesForm;
import com.example.tajappointments.ServiceForm.ServicesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class BusinessController {

    private static final Logger log = LoggerFactory.getLogger(BusinessController.class);
    private final BusinessService businessService;
    private final ServicesService servicesService;

    public BusinessController(BusinessService businessService, ServicesService servicesService) {
        this.businessService = businessService;
        this.servicesService = servicesService;
    }

    @PostMapping("/business")
    public Business create(@RequestBody Business business){
        return businessService.create(business);
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
        x.setBusiness_name(businessName);
        x.setOwnerId(ownerId);

        businessService.create(x);

        return "new business";
    }

    @PostMapping("/api/new/service")
    public String newService(@RequestBody ServicesForm[] form) {

        try {

            for(ServicesForm currentService : form){
                Services newService = new Services();

                newService.setName(currentService.getServiceName());
                newService.setDescription(currentService.getServiceDescription());
                newService.setDuration(currentService.getServiceDuration());
                newService.setCost(currentService.getServiceCost());
                newService.setBusinessId(currentService.getBusinessId());

                servicesService.create(newService);
                businessService.addToServicesById(UUID.fromString(currentService.getBusinessId()) , newService.getId());
            }

            return "Services Array";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @PostMapping("/api/remove/service")
    public String removeService(@RequestBody ServicesForm form) {

        String serviceQuery = form.getServiceQuery();
        String businessId = form.getBusinessId();

        try {

            businessService.removeServicesById(UUID.fromString(businessId), UUID.fromString(serviceQuery));
            servicesService.removeById(UUID.fromString(serviceQuery));

            return "Service Removed";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @PostMapping("/api/edit/service")
//    public String editService(@RequestBody )
}

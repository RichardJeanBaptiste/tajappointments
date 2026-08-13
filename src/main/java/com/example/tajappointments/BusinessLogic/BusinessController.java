package com.example.tajappointments.BusinessLogic;

import com.example.tajappointments.ServiceForm.ServicesForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BusinessController {

    private static final Logger log = LoggerFactory.getLogger(BusinessController.class);
    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
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
    public String newService(@RequestBody ServicesForm form) {

        String serviceName = form.getServiceName();
        String serviceDescription = form.getServiceDescription();
        String serviceCost = form.getServiceCost();
        String serviceDuration = form.getServiceDuration();
        String businessId = form.getBusinessId();

        ArrayList<String> info = new ArrayList<>(
                List.of(serviceDescription, serviceDuration, serviceCost)
        );

        Map<String, List<String>> service = new HashMap<>();

        service.put(serviceName , info);

        try {



            return "Service Created" + " " + serviceName + " " + serviceDescription + " " + serviceCost + " " + serviceDuration;
        } catch (Exception e) {
            System.out.println(e.toString());
            return "Failed To Create New Service";
        }
    }
}

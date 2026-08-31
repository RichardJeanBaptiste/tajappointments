package com.example.tajappointments.BusinessLogic;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class BusinessController {

    //private static final Logger log = LoggerFactory.getLogger(BusinessController.class);
    private final BusinessService businessService;

    public BusinessController(BusinessService businessService ) {
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

    
}

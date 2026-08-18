package com.example.tajappointments.BusinessLogic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Service
public class BusinessService {

    private final BusinessRepository businessRepository;

    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    public Business create(Business business){
        return businessRepository.save(business);
    }

    public Business findById(UUID id) {
        return businessRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Business not found"));
    }

    public void editFields(UUID businessId, HashMap<String, String> fields) {

        Business business = findById(businessId);

        for (String field : fields.keySet()) {

            String currentValue = fields.get(field);

           switch(field) {
               case "name":
                    if (!currentValue.isEmpty()) {
                        business.setName(currentValue);
                    }
                    break;
               case "email":
                    if (!currentValue.isEmpty()) {
                        business.setEmail(currentValue);
                    }
                   break;
               case "address":
                   if(!currentValue.isEmpty()) {
                       business.setAddress(currentValue);
                   }
                   break;
               case "business_name":
                   if(!currentValue.isEmpty()){
                       business.setBusinessName(currentValue);
                   }
               default:
                   break;
           }
        }

        businessRepository.save(business);
    }

    public void addToServicesById(UUID businessId, ArrayList<UUID> serviceIds) {

        Business business = findById(businessId);


        for (UUID service: serviceIds) {
            business.getServices().add(service);
        }


        businessRepository.save(business);
    }

    public void removeServicesById(UUID businessId,  ArrayList<UUID> serviceIds) {

        Business business = findById(businessId);

        for (UUID service: serviceIds) {
            business.getServices().remove(service);
        }


        businessRepository.save(business);
    }


    public void changeNameById(UUID businessId, String newName) {

        Business business = findById(businessId);

        business.setName(newName);

        businessRepository.save(business);
    }

    public void changeEmailById(UUID businessId, String email) {

        Business business = findById(businessId);

        business.setName(email);

        businessRepository.save(business);
    }

}

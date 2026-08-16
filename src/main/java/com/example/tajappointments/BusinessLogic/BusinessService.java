package com.example.tajappointments.BusinessLogic;
import org.springframework.stereotype.Service;

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

    public void addToServicesById(UUID businessId, UUID serviceId) {

        Business business = findById(businessId);

        business.getServices().add(serviceId);

        businessRepository.save(business);
    }

    public void removeServicesById(UUID businessId, UUID serviceId) {

        Business business = findById(businessId);

        business.getServices().remove(serviceId);

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

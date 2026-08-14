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

        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Business not found"));


        business.getServices().add(serviceId);

        businessRepository.save(business);
    }
}

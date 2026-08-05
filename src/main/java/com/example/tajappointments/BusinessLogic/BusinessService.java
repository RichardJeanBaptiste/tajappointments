package com.example.tajappointments.BusinessLogic;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    private final BusinessRepository businessRepository;

    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    public Business create(Business business){
        return businessRepository.save(business);
    }
}

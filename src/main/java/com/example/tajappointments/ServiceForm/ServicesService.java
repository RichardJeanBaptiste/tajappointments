package com.example.tajappointments.ServiceForm;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ServicesService {

    private final ServicesRepository servicesRepository;

    public ServicesService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    public Services create(Services services) {
        return servicesRepository.save(services);
    }

    public Services findById(UUID id) {
        return servicesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service Not Found"));
    }

    public void removeById(UUID id) {

        Services service = findById(id);

        servicesRepository.delete(service);
    }

    public void addMultipleServices(List<Services> services) {

        List<Services> newServices = new ArrayList<>();

        newServices.addAll(services);

        servicesRepository.saveAll(newServices);
    }
}

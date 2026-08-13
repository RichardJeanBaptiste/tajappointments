package com.example.tajappointments.ServiceForm;


import org.springframework.stereotype.Service;

@Service
public class ServicesService {

    private final ServicesRepository servicesRepository;

    public ServicesService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    public Services create(Services services) {
        return servicesRepository.save(services);
    }
}

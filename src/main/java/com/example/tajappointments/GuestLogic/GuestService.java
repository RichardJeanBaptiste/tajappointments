package com.example.tajappointments.GuestLogic;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository){
        this.guestRepository = guestRepository;
    }

    public Guest create(Guest guest){
        return guestRepository.save(guest);
    }

    public Guest findById(UUID id) {
        String errRes = "Guest Not Found - " + id.toString();
        return guestRepository.findById(id).orElseThrow(() -> new RuntimeException(errRes));
    }

    public void removeById(UUID id) {
        Guest x = findById(id);
        guestRepository.delete(x);
    }

    

    
}

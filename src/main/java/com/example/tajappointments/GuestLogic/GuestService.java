package com.example.tajappointments.GuestLogic;
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
}

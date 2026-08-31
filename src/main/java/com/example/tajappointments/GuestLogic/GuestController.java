package com.example.tajappointments.GuestLogic;

import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class GuestController {
    

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }


    @PostMapping("/api/new/guest")
    public ResponseEntity<String> createGuest(@RequestBody GuestForm form) {
        //TODO: process POST request

        String email = form.getGuestEmail();
        String name = form.getGuestName();

        Guest x = new Guest();

        x.setEmail(email);
        x.setName(name);

        guestService.create(x);

        return ResponseEntity.ok("Guest Account Created");
    }

    @PostMapping("/api/remove/guest")
    public ResponseEntity<String> removeGuest(@RequestBody GuestForm form) {
        //TODO: process guest remove request

        String id = form.getGuestId();

        guestService.removeById(UUID.fromString(id));

        return ResponseEntity.ok("Guest Removed");
    }
    
}

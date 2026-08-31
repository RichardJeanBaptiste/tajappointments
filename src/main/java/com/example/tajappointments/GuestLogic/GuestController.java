package com.example.tajappointments.GuestLogic;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class GuestController {
    

    public GuestController() {

    }


    @PostMapping("/api/new/guest")
    public ResponseEntity<String> createGuest(@RequestBody GuestForm form) {
        //TODO: process POST request


        
        return ResponseEntity.ok("Guest Account Created");
    }

    @PostMapping("/api/remove/guest")
    public ResponseEntity<String> removeGuest(@RequestBody GuestForm form) {
        //TODO: process guest remove request

        return ResponseEntity.ok("Guest Removed");
    }
    
}

package com.example.tajappointments.UserLogic;

import java.util.List;
import java.util.UUID;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/api/getuserinfo/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable UUID id) {

        //System.out.println(id.toString());

        User x = userService.getUserById(id);

        List<UUID> businessIds = x.getBusinessIds();

        for (UUID businessId : businessIds) {
            System.out.println(businessId);
        }

        System.out.println(x.getClientId());

        return ResponseEntity.ok("User info returned");
    }


}

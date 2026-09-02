package com.example.tajappointments;

import com.example.tajappointments.BusinessLogic.Business;
import com.example.tajappointments.BusinessLogic.BusinessForm;
import com.example.tajappointments.BusinessLogic.BusinessService;
import com.example.tajappointments.ClientLogic.Client;
import com.example.tajappointments.ClientLogic.ClientForm;
import com.example.tajappointments.ClientLogic.ClientService;
import com.example.tajappointments.GuestLogic.Guest;
import com.example.tajappointments.GuestLogic.GuestForm;
import com.example.tajappointments.GuestLogic.GuestService;
import com.example.tajappointments.UserLogic.EmailAlreadyExistsException;
import com.example.tajappointments.UserLogic.User;
import com.example.tajappointments.UserLogic.UserForm;
import com.example.tajappointments.UserLogic.UserService;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;



/**
 * 
 * TODO:
 *      Add Auth to routes 
 * 
 * 
 * LoginController
 * 
 *  Register -> new user created - role selected (Business, Client, Employee) - Sent to relevant portal
 *  Login -> check username/pass -> check for businessId/clientId -> Send to relevant portal
 *   
 */

@RestController
public class LoginController {

    //private final BusinessService businessService;

    private final ClientService clientService;
    private final GuestService guestService;
    private final UserService userService;
    private final BusinessService businessService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginController(BusinessService businessService, ClientService clientService, GuestService guestService, UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {

        this.businessService = businessService;
        this.clientService = clientService;
        this.guestService = guestService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }



    @PostMapping("/client")
    public Client create(@RequestBody Client client){
        return clientService.create(client);
    }

    @PostMapping("/guest")
    public Guest create(@RequestBody Guest guest){
        return guestService.create(guest);
    }

    @PostMapping("/user")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }


    @PostMapping("/api/auth/login")
    public ResponseEntity<?> loginHandler(@RequestBody LoginForm form) {

        String email = form.getLoginEmail();
        String password = form.getLoginPassword();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email,
                            password
                    )
            );

            String token = jwtService.generateToken(email);

            return ResponseEntity.ok(Map.of("token",token));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Username or Password failed");
        }
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<?> userHandler(@RequestBody UserForm form){

       try {

            String email = form.getUserEmail();
            String password = form.getUserPassword();
            String role = form.getRole();

            User x = new User();

            x.setEmail(email);
            x.setPassword(password);
            x.setRole(role);

            userService.create(x);

            String token = jwtService.generateToken(email);

            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("token", token));
            
        } catch (EmailAlreadyExistsException e) {
            
            return ResponseEntity.badRequest().body("Email already exists");
        }
    }



    @PostMapping("/api/new/client")
    public ResponseEntity<String> newClientHandler(ClientForm form) {

        String name = form.getClientName();
        String email = form.getClientEmail();

        Client x = new Client();

        x.setName(name);
        x.setEmail(email);

        clientService.create(x);

        String res = "New Client Created - " + email;

        return ResponseEntity.ok(res);
    }



    @PostMapping("/new/guest")
    public ResponseEntity<String> guestHandler(GuestForm form) {

        String email = form.getGuestEmail();
        String name = form.getGuestName();

        Guest x = new Guest();
        x.setEmail(email);
        x.setName(name);

        guestService.create(x);

        String res = "New Guest Created - " + email;

        return ResponseEntity.ok(res);
    }
}


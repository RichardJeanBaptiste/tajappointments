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
import com.example.tajappointments.UserLogic.User;
import com.example.tajappointments.UserLogic.UserForm;
import com.example.tajappointments.UserLogic.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    private final BusinessService businessService;

    private final ClientService clientService;

    private final GuestService guestService;

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    public LoginController(BusinessService businessService, ClientService clientService, GuestService guestService, UserService userService, AuthenticationManager authenticationManager) {

        this.businessService = businessService;
        this.clientService = clientService;
        this.guestService = guestService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
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
    public String loginHandler(@RequestBody LoginForm form) {


        String email = form.getLoginEmail();
        String password = form.getLoginPassword();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email,
                            password
                    )
            );
            return "Login successful";
        } catch (AuthenticationException e) {
            return "Username or Password failed";
        }
    }

    @PostMapping("/api/auth/register")
    public String userHandler(@RequestBody UserForm form){

        String email = form.getUserEmail();
        String password = form.getUserPassword();
        String role = form.getRole();

        User x = new User();

        x.setEmail(email);
        x.setPassword(password);
        x.setRole(role);

        userService.create(x);

        return "User Registered";
    }



    @PostMapping("/new/client")
    public String newClientHandler(ClientForm form) {

        String name = form.getClientName();
        String email = form.getClientEmail();

        Client x = new Client();

        x.setName(name);
        x.setEmail(email);

        clientService.create(x);

        return "new client";
    }



    @PostMapping("/new/guest")
    public String guestHandler(GuestForm form) {

        String email = form.getGuestEmail();
        String name = form.getGuestName();

        Guest x = new Guest();
        x.setEmail(email);
        x.setName(name);

        guestService.create(x);

        return "new guest";
    }
}


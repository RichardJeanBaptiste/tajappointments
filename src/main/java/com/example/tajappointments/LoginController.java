package com.example.tajappointments;

import com.example.tajappointments.BusinessLogic.Business;
import com.example.tajappointments.BusinessLogic.BusinessService;
import com.example.tajappointments.ClientLogic.Client;
import com.example.tajappointments.ClientLogic.ClientService;
import com.example.tajappointments.GuestLogic.Guest;
import com.example.tajappointments.GuestLogic.GuestForm;
import com.example.tajappointments.GuestLogic.GuestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final BusinessService businessService;

    private final ClientService clientService;

    private final GuestService guestService;

    public LoginController(BusinessService businessService, ClientService clientService, GuestService guestService) {

        this.businessService = businessService;
        this.clientService = clientService;
        this.guestService = guestService;
    }

    @PostMapping("/business")
    public Business create(@RequestBody Business business){
        return businessService.create(business);
    }

    @PostMapping("/client")
    public Client create(@RequestBody Client client){
        return clientService.create(client);
    }

    @PostMapping("/guest")
    public Guest create(@RequestBody Guest guest){
        return guestService.create(guest);
    }

    @PostMapping("/login")
    public String loginHandler(LoginForm form) {

        String email = form.getLoginEmail();
        String password = form.getLoginPassword();
        return email + " " + password;
    }


    @PostMapping("/new/business")
    public String newBusinessHandler(RegisterForm form) {

        String name = form.getRegistration_name();
        String email = form.getRegistrationEmail();

        Business x = new Business();

        x.setName(name);
        x.setEmail(email);
        x.setBusiness_name("Test Business");

        businessService.create(x);

        return "new business";
    }

    @PostMapping("/new/client")
    public String newClientHandler(RegisterForm form) {

        String name = form.getRegistration_name();
        String email = form.getRegistrationEmail();

        Client x = new Client();

        x.setName(name);
        x.setEmail(email);

        clientService.create(x);

        return "new business";
    }



    @PostMapping("/new/guest")
    public String guestHandler(GuestForm form) {

        String username = form.getGuest_username();
        String email = form.getGuest_email();

        Guest x = new Guest();
        x.setName(username);
        x.setEmail(email);

        guestService.create(x);

        return "new guest";
    }
}


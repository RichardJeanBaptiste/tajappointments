package com.example.tajappointments;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public String loginHandler(LoginForm form) {

        String username = form.getLogin_username();
        String password = form.getLogin_password();
        return username + " " + password;
    }

    @PostMapping("/registration")
    public String registrationHandler(RegisterForm form) {
        String username = form.getRegistration_username();
        String password = form.getRegistration_password();

        return username + " " + password;
    }

    @PostMapping("/guest")
    public void guestHandler() {
        System.out.println("guest handler");
    }
}

class LoginForm {
    private String login_username;
    private String login_password;


    public void setLogin_username(String login_username) {
        this.login_username = login_username;
    }

    public String getLogin_username() {
        return login_username;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }

    public String getLogin_password() {
        return login_password;
    }
}

class RegisterForm {
    private String registration_username;
    private String registration_password;

    public void setRegistration_username(String registration_username) {
        this.registration_username = registration_username;
    }

    public String getRegistration_username() {
        return registration_username;
    }

    public void setRegistration_password(String registration_password){
        this.registration_password = registration_password;
    }

    public String getRegistration_password(){
        return registration_password;
    }
}


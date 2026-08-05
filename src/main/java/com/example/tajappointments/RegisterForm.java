package com.example.tajappointments;

public class RegisterForm {
    private String registration_username;
    private String registration_password;
    private String account_type;
    private String registration_name;
    private String registration_email;

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

    public void setAccount_type(String account_type){
        this.account_type = account_type;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setRegistration_name(String name) {
        this.registration_name = name;
    }

    public String getRegistration_name() {
        return registration_name;
    }

    public void setRegistration_email(String registration_email) {
        this.registration_email = registration_email;
    }

    public String getRegistration_email() {
        return registration_email;
    }
}

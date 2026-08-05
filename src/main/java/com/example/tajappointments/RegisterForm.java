package com.example.tajappointments;

public class RegisterForm {

    private String registrationEmail;
    private String registrationPassword;
    private String registrationName;
    private String businessName;


    public void setRegistrationEmail(String registrationEmail) {
        this.registrationEmail = registrationEmail;
    }

    public String getRegistrationEmail() {
        return registrationEmail;
    }

    public void setRegistrationPassword(String registrationPassword){
        this.registrationPassword = registrationPassword;
    }

    public String getRegistrationPassword(){
        return registrationPassword;
    }

    public void setRegistrationName(String name) {
        this.registrationName = name;
    }

    public String getRegistrationName() {
        return registrationName;
    }

    public void setBusinessName(String businessName){
        this.businessName = businessName;
    }

    public String getBusinessName() {
        return businessName;
    }


}

package com.example.tajappointments.BusinessLogic;

public class BusinessForm {

    private String businessEmail;
    private String businessPassword;
    private String businessName;
    private String ownerName;
    private String ownerId;

    public void setBusinessEmail(String businessEmail){
        this.businessEmail = businessEmail;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessPassword(String businessPassword) {
        this.businessPassword = businessPassword;
    }

    public String getBusinessPassword() {
        return businessPassword;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerId() {
        return ownerId;
    }
}

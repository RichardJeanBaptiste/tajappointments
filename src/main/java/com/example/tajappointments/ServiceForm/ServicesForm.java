package com.example.tajappointments.ServiceForm;

import java.util.UUID;

public class ServicesForm {

    private String serviceName;
    private String serviceCost;
    private String serviceDuration;
    private String serviceDescription;
    private String serviceQuery;
    private String businessId;


    public void setServiceName(String serviceName){
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceCost(String serviceCost) {
        this.serviceCost = serviceCost;
    }

    public String getServiceCost() {
        return serviceCost;
    }

    public void setServiceDuration(String serviceDuration) {
        this.serviceDuration = serviceDuration;
    }

    public String getServiceDuration() {
        return serviceDuration;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceQuery(String serviceQuery){
        this.serviceQuery = serviceQuery;
    }

    public String getServiceQuery() {
        return serviceQuery;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessId() {
        return businessId;
    }

}

package com.example.tajappointments.ServiceLogic;


import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;


/**
 * private String serviceName;
 *     private String serviceCost;
 *     private String serviceDuration;
 *     private String serviceDescription;
 *     private String businessId;
 */


@Entity
@Table(name = "services")
public class Services {

    @Id
    private UUID id;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    private String name;
    private String cost;
    private int duration;
    private String description;
    private String businessId;
    private boolean availability;

    public Services() {
        this.availability = true;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCost() {
        return cost;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public boolean getAvailability() {
        return availability;
    }

}

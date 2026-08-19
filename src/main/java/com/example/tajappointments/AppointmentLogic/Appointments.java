package com.example.tajappointments.AppointmentLogic;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "appointments")
public class Appointments {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    private UUID businessId;
    private UUID clientId;
    private UUID serviceId;

    private Instant startTime;
    private Instant endTime;

    private String status;

    public String getId() {
        return id.toString();
    }

    public void setBusinessId(UUID businessId) {
        this.businessId = businessId;
    }

    public String getBusinessId() {
        return businessId.toString();
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId.toString();
    }

    public void setServiceId(UUID serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceId() {
        return serviceId.toString();
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime.toString();
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime.toString();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

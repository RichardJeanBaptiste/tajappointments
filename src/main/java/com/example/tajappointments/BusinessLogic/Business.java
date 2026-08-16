package com.example.tajappointments.BusinessLogic;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.*;


/*
 * created_at
 * business_name
 * client list
 * available dates
 * services
 * appointments
 * reviews
 *
 */

@Entity
@Table(name = "business")
public class Business {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    private String name;
    private String email;
    private String business_name;
    private String ownerId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<String> clientList;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<String> availableDates;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<UUID> services;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, List<String>> appointments;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<String> reviews;

    public Business() {
        this.clientList = new ArrayList<String>();
        this.availableDates = new ArrayList<String>();
        this.services = new ArrayList<UUID>();
        this.appointments = new HashMap<String, List<String>>();
        this.reviews = new ArrayList<String>();
    }

    public String getAvailableDates() {
        return availableDates.toString();
    }

    public String getClientList() {
        return clientList.toString();
    }

    public List<UUID> getServices() {
        return services;
    }

    public String getAppointments() {
        return appointments.toString();
    }

    public String getReviews() {
        return reviews.toString();
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerId() {
        return ownerId;
    }



}

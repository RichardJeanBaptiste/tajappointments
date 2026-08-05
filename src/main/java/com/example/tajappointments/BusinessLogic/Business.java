package com.example.tajappointments.BusinessLogic;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/*
 * created_at
 * business_name
 * client list
 * available dates
 * services
 * appointments
 * reviews
 * */

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
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<String> clientList;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<String> availableDates;

    public Business() {
        this.clientList = new ArrayList<String>();
        this.availableDates = new ArrayList<String>();
    }

    public String getAvailableDates() {
        return availableDates.toString();
    }

    public String getClientList() {
        return clientList.toString();
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

}

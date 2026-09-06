package com.example.tajappointments.UserLogic;

import jakarta.persistence.*;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    private String email;
    private String password;
    private String role;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<UUID> businessIds = new ArrayList<UUID>();

    private UUID clientId;


    public String getId() {
        return id.toString();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public List<UUID> getBusinessIds() {
        return businessIds;
    }

    public void setBusinessIds() {
        this.businessIds = new ArrayList<>();
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public UUID getClientId() {
        return clientId;
    }
}

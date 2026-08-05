package com.example.tajappointments.ClientLogic;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import java.time.OffsetDateTime;
import java.util.UUID;

/*
*   name
*   email
*   appointments
* */

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    private String name;
    private String email;

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
}

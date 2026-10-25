package com.foodresq.recipientservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="recipient")
public class Recipient {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String phone;
    private String location;
    private String urgency;
    private Double capacityKg;
    private Boolean pickupAvailable;
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getLocation() { return location; }
    public String getUrgency() { return urgency; }
    public Double getCapacityKg() { return capacityKg; }
    public Boolean getPickupAvailable() { return pickupAvailable; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setLocation(String location) { this.location = location; }
    public void setUrgency(String urgency) { this.urgency = urgency; }
    public void setCapacityKg(Double capacityKg) { this.capacityKg = capacityKg; }
    public void setPickupAvailable(Boolean pickupAvailable) { this.pickupAvailable = pickupAvailable; }
}

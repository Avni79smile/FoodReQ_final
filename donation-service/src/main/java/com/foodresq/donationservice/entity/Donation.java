package com.foodresq.donationservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="donation")
public class Donation {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String id;
    private String donorId;
    private String foodType;
    private Double quantityKg;
    private String location;
    private String expiryTime;
    private String status;
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDonorId() { return donorId; }
    public String getFoodType() { return foodType; }
    public Double getQuantityKg() { return quantityKg; }
    public String getLocation() { return location; }
    public String getExpiryTime() { return expiryTime; }
    public String getStatus() { return status; }
    public void setDonorId(String donorId) { this.donorId = donorId; }
    public void setFoodType(String foodType) { this.foodType = foodType; }
    public void setQuantityKg(Double quantityKg) { this.quantityKg = quantityKg; }
    public void setLocation(String location) { this.location = location; }
    public void setExpiryTime(String expiryTime) { this.expiryTime = expiryTime; }
    public void setStatus(String status) { this.status = status; }
}

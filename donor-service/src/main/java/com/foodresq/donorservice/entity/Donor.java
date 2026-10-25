package com.foodresq.donorservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="donor")
public class Donor {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String phone;
    private String location;
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getLocation() { return location; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setLocation(String location) { this.location = location; }
}

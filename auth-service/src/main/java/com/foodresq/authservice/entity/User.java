package com.foodresq.authservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String id;
    private String email;
    private String password;
    private String role;
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
}

package com.foodresq.notificationservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="notification")
public class Notification {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String id;
    private String recipient;
    private String message;
    private String type;
    private String status;
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public String getType() { return type; }
    public String getStatus() { return status; }
    public void setRecipient(String recipient) { this.recipient = recipient; }
    public void setMessage(String message) { this.message = message; }
    public void setType(String type) { this.type = type; }
    public void setStatus(String status) { this.status = status; }
}

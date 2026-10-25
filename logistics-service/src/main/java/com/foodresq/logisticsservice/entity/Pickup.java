package com.foodresq.logisticsservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="pickup")
public class Pickup {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String id;
    private String donationId;
    private String recipientId;
    private String scheduledAt;
    private String status;
    public String getDonationId() { return donationId; }
    public String getRecipientId() { return recipientId; }
    public String getScheduledAt() { return scheduledAt; }
    public String getStatus() { return status; }
    public void setDonationId(String donationId) { this.donationId = donationId; }
    public void setRecipientId(String recipientId) { this.recipientId = recipientId; }
    public void setScheduledAt(String scheduledAt) { this.scheduledAt = scheduledAt; }
    public void setStatus(String status) { this.status = status; }
}

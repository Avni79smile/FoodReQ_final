package com.foodresq.matchingservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="matching_result")
public class Match {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String id;
    private String donationId;
    private String recipientId;
    private Double ruleScore;
    private Double mlProbability;
    private Double finalScore;
    private String explanation;
    private String status;
    public String getDonationId() { return donationId; }
    public String getRecipientId() { return recipientId; }
    public Double getRuleScore() { return ruleScore; }
    public Double getMlProbability() { return mlProbability; }
    public Double getFinalScore() { return finalScore; }
    public String getExplanation() { return explanation; }
    public String getStatus() { return status; }
    public void setDonationId(String donationId) { this.donationId = donationId; }
    public void setRecipientId(String recipientId) { this.recipientId = recipientId; }
    public void setRuleScore(Double ruleScore) { this.ruleScore = ruleScore; }
    public void setMlProbability(Double mlProbability) { this.mlProbability = mlProbability; }
    public void setFinalScore(Double finalScore) { this.finalScore = finalScore; }
    public void setExplanation(String explanation) { this.explanation = explanation; }
    public void setStatus(String status) { this.status = status; }
}

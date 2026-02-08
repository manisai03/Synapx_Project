package com.example.demo.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "claims")
public class ClaimEntity {

    @Id
    private String claimId;

    private String policyNumber;
    private String policyHolderName;
    private String claimType;
    private String location;
    private String incidentDate;
    private Integer estimatedDamage;
    private Double averageConfidence;
    private String route;

    @Enumerated(EnumType.STRING)
    private ClaimStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public String getClaimId() { return claimId; }
    public void setClaimId(String claimId) { this.claimId = claimId; }

    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }

    public String getPolicyHolderName() { return policyHolderName; }
    public void setPolicyHolderName(String policyHolderName) { this.policyHolderName = policyHolderName; }

    public String getClaimType() { return claimType; }
    public void setClaimType(String claimType) { this.claimType = claimType; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getIncidentDate() { return incidentDate; }
    public void setIncidentDate(String incidentDate) { this.incidentDate = incidentDate; }

    public Integer getEstimatedDamage() { return estimatedDamage; }
    public void setEstimatedDamage(Integer estimatedDamage) { this.estimatedDamage = estimatedDamage; }

    public Double getAverageConfidence() { return averageConfidence; }
    public void setAverageConfidence(Double averageConfidence) { this.averageConfidence = averageConfidence; }

    public String getRoute() { return route; }
    public void setRoute(String route) { this.route = route; }

    public ClaimStatus getStatus() { return status; }
    public void setStatus(ClaimStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}

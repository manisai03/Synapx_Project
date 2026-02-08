package com.example.demo.Model;

import java.util.Map;

public class ClaimData {

    private String policyNumber;
    private String policyHolderName;
    private String incidentDate;
    private String location;
    private Integer estimatedDamage;
    private String claimType;
    private String description;

    private Map<String,Integer> confidenceScores;

    public Map<String,Integer> getConfidenceScores() { return confidenceScores; }
    public void setConfidenceScores(Map<String,Integer> confidenceScores) { this.confidenceScores = confidenceScores; }

    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }

    public String getPolicyHolderName() { return policyHolderName; }
    public void setPolicyHolderName(String policyHolderName) { this.policyHolderName = policyHolderName; }

    public String getIncidentDate() { return incidentDate; }
    public void setIncidentDate(String incidentDate) { this.incidentDate = incidentDate; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Integer getEstimatedDamage() { return estimatedDamage; }
    public void setEstimatedDamage(Integer estimatedDamage) { this.estimatedDamage = estimatedDamage; }

    public String getClaimType() { return claimType; }
    public void setClaimType(String claimType) { this.claimType = claimType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

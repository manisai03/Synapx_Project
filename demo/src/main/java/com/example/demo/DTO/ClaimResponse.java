package com.example.demo.DTO;

import com.example.demo.Model.ClaimData;
import java.util.List;

public class ClaimResponse {

    private String fileName;
    private String claimId;
    private long processingTimeMs;
    private double averageConfidence;

    private ClaimData extractedFields;
    private List<String> missingFields;
    private String recommendedRoute;
    private String reasoning;

    public ClaimResponse(String fileName,
                         String claimId,
                         long processingTimeMs,
                         double averageConfidence,
                         ClaimData extractedFields,
                         List<String> missingFields,
                         String recommendedRoute,
                         String reasoning) {

        this.fileName = fileName;
        this.claimId = claimId;
        this.processingTimeMs = processingTimeMs;
        this.averageConfidence = averageConfidence;
        this.extractedFields = extractedFields;
        this.missingFields = missingFields;
        this.recommendedRoute = recommendedRoute;
        this.reasoning = reasoning;
    }

    public String getFileName() { return fileName; }
    public String getClaimId() { return claimId; }
    public long getProcessingTimeMs() { return processingTimeMs; }
    public double getAverageConfidence() { return averageConfidence; }
    public ClaimData getExtractedFields() { return extractedFields; }
    public List<String> getMissingFields() { return missingFields; }
    public String getRecommendedRoute() { return recommendedRoute; }
    public String getReasoning() { return reasoning; }
}

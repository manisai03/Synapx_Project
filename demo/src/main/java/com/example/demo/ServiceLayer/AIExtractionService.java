package com.example.demo.ServiceLayer;

import com.example.demo.Model.ClaimData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.*;

@Service
public class AIExtractionService {

    static final Logger log = LoggerFactory.getLogger(AIExtractionService.class);

    public ClaimData extractFields(String text) {

        ClaimData claim = new ClaimData();
        Map<String,Integer> confidence = new HashMap<>();

        claim.setPolicyNumber(extract(text,"(POLICY NUMBER|POLICY NO)[:\\s]*([A-Z0-9-]+)",confidence,"policyNumber"));
        claim.setPolicyHolderName(extract(text,"(INSURED NAME|POLICY HOLDER)[:\\s]*([A-Za-z ]+)",confidence,"policyHolderName"));
        claim.setIncidentDate(extract(text,"(DATE OF LOSS|INCIDENT DATE)[:\\s]*([0-9/\\-]+)",confidence,"incidentDate"));
        claim.setLocation(extract(text,"LOCATION[:\\s]*([A-Za-z ]+)",confidence,"location"));
        claim.setClaimType(extract(text,"CLAIM TYPE[:\\s]*([A-Za-z ]+)",confidence,"claimType"));

        String damage = extract(text,"(ESTIMATED DAMAGE|DAMAGE AMOUNT)[:\\s]*([0-9]+)",confidence,"estimatedDamage");

        if(damage!=null) claim.setEstimatedDamage(Integer.parseInt(damage));

        claim.setDescription(text);
        claim.setConfidenceScores(confidence);
        log.info("Extracting FNOL fields from input text");
        return claim;
    }

    private String extract(String text,String regex,Map<String,Integer> conf,String field){
        Matcher matcher = Pattern.compile(regex,Pattern.CASE_INSENSITIVE).matcher(text);
        if(matcher.find()){
            conf.put(field,90);
            return matcher.group(matcher.groupCount()).trim();
        }
        conf.put(field,0);
        return null;
    }

    public double calculateAverageConfidence(Map<String,Integer> scores){
        return scores.values().stream().mapToInt(Integer::intValue).average().orElse(0);
    }
}

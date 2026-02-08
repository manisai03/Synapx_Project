package com.example.demo.ServiceLayer;

import com.example.demo.Config.FraudConfig;
import com.example.demo.Model.ClaimData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoutingService {

    private static final Logger log = LoggerFactory.getLogger(RoutingService.class);

    private final FraudConfig fraudConfig;

    public RoutingService(FraudConfig fraudConfig) {
        this.fraudConfig = fraudConfig;
    }

    public String route(ClaimData claim, List<String> missing){

        String description = Optional.ofNullable(claim.getDescription())
                .orElse("")
                .toLowerCase();

        if(!missing.isEmpty()){
            log.info("Routing to Manual Review due to missing fields");
            return "Manual Review";
        }

        for(String keyword : fraudConfig.getFraudKeywords()){
            if(description.contains(keyword.toLowerCase())){
                log.warn("Fraud keyword detected: {}", keyword);
                return "Investigation";
            }
        }

        if("injury".equalsIgnoreCase(claim.getClaimType())){
            log.info("Routing to Specialist Queue");
            return "Specialist Queue";
        }

        if(claim.getEstimatedDamage()!=null && claim.getEstimatedDamage()<25000){
            log.info("Routing to Fast Track");
            return "Fast Track";
        }

        return "Standard Processing";
    }

    public String reason(ClaimData claim,List<String> missing){

        if(!missing.isEmpty())
            return "Mandatory fields missing: " + missing;

        if("injury".equalsIgnoreCase(claim.getClaimType()))
            return "Injury claims require specialist review.";

        return "Standard processing.";
    }
}

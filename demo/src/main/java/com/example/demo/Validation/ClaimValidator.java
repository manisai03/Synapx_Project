package com.example.demo.Validation;

import com.example.demo.Model.ClaimData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Component
public class ClaimValidator {

    public List<String> validate(ClaimData claim) {

        List<String> missing = new ArrayList<>();

        if (claim.getPolicyNumber() == null)
            missing.add("Policy Number");

        if (isNull(claim.getPolicyHolderName()))
            missing.add("Policy Holder Name");

        if (isNull(claim.getIncidentDate()))
            missing.add("Incident Date");

        if (isNull(claim.getLocation()))
            missing.add("Location");

        if (claim.getEstimatedDamage() == null)
            missing.add("Estimated Damage");

        if (claim.getClaimType() == null)
            missing.add("Claim Type");

        return missing;
    }

    private boolean isNull(String value) {
        return value == null || value.trim().isEmpty();
    }
}

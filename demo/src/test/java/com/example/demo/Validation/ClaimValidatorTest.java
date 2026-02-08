package com.example.demo.Validation;

import com.example.demo.Model.ClaimData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClaimValidatorTest {

    private final ClaimValidator validator = new ClaimValidator();

    @Test
    void shouldReturnMissingFields() {

        ClaimData data = new ClaimData();
        data.setPolicyNumber("POL-111");

        List<String> missing = validator.validate(data);

        assertTrue(missing.contains("Policy Holder Name"));
        assertTrue(missing.contains("Claim Type"));
    }

    @Test
    void shouldReturnEmptyWhenValid() {

        ClaimData data = new ClaimData();
        data.setPolicyNumber("POL-111");
        data.setPolicyHolderName("John");
        data.setIncidentDate("01/01/2026");
        data.setLocation("Hyderabad");
        data.setEstimatedDamage(10000);
        data.setClaimType("Vehicle");

        List<String> missing = validator.validate(data);

        assertTrue(missing.isEmpty());
    }
}

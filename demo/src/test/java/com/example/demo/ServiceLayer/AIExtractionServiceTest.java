package com.example.demo.ServiceLayer;

import com.example.demo.Model.ClaimData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AIExtractionServiceTest {

    private final AIExtractionService service = new AIExtractionService();

    @Test
    void shouldExtractFieldsFromText() {

        String text = """
                POLICY NUMBER: POL-12345
                INSURED NAME: John Doe
                DATE OF LOSS: 01/01/2026
                LOCATION: Hyderabad
                CLAIM TYPE: Vehicle
                ESTIMATED DAMAGE: 20000
                """;

        ClaimData data = service.extractFields(text);

        assertEquals("POL-12345", data.getPolicyNumber());
        assertEquals("John Doe", data.getPolicyHolderName());
        assertEquals("Vehicle", data.getClaimType());
    }
}

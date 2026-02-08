package com.example.demo.ServiceLayer;

import com.example.demo.Config.FraudConfig;
import com.example.demo.Model.ClaimData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoutingServiceTest {

    private RoutingService routingService;

    @BeforeEach
    void setup() {
        FraudConfig config = mock(FraudConfig.class);
        when(config.getFraudKeywords()).thenReturn(List.of("fraud"));
        routingService = new RoutingService(config);
    }

    @Test
    void shouldRouteToSpecialist() {

        ClaimData data = new ClaimData();
        data.setClaimType("Injury");
        data.setEstimatedDamage(50000);

        String route = routingService.route(data, List.of());

        assertEquals("Specialist Queue", route);
    }

    @Test
    void shouldRouteToManualReviewWhenMissing() {

        ClaimData data = new ClaimData();

        String route = routingService.route(data, List.of("Policy"));

        assertEquals("Manual Review", route);
    }
}

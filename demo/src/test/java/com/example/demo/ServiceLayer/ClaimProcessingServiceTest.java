package com.example.demo.ServiceLayer;

import com.example.demo.Repository.ClaimRepository;
import com.example.demo.Validation.ClaimValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.*;

class ClaimProcessingServiceTest {

    @Test
    void shouldProcessFile() throws Exception {

        PdfExtractionService pdfService = Mockito.mock(PdfExtractionService.class);
        AIExtractionService aiService = new AIExtractionService();
        ClaimValidator validator = new ClaimValidator();
        RoutingService routingService = Mockito.mock(RoutingService.class);
        ClaimRepository repository = Mockito.mock(ClaimRepository.class);

        Mockito.when(pdfService.extractText(Mockito.any()))
                .thenReturn("""
                        POLICY NUMBER: POL-123
                        INSURED NAME: John
                        DATE OF LOSS: 01/01/2026
                        LOCATION: Chennai
                        CLAIM TYPE: Vehicle
                        ESTIMATED DAMAGE: 10000
                        """);

        Mockito.when(routingService.route(Mockito.any(), Mockito.any()))
                .thenReturn("Fast Track");

        Mockito.when(routingService.reason(Mockito.any(), Mockito.any()))
                .thenReturn("Test Reason");

        ClaimProcessingService service =
                new ClaimProcessingService(pdfService, aiService, validator,
                        routingService, repository);

        MockMultipartFile file =
                new MockMultipartFile("file", "test.pdf",
                        "application/pdf", "dummy".getBytes());

        var response = service.process(file);

        assertNotNull(response.getClaimId());
        assertEquals("Fast Track", response.getRecommendedRoute());
    }
}

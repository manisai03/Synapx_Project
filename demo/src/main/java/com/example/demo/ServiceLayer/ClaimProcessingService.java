package com.example.demo.ServiceLayer;

import com.example.demo.DTO.ClaimResponse;
import com.example.demo.Entity.ClaimEntity;
import com.example.demo.Entity.ClaimStatus;
import com.example.demo.Model.ClaimData;
import com.example.demo.Repository.ClaimRepository;
import com.example.demo.Util.ClaimIdGenerator;
import com.example.demo.Validation.ClaimValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class ClaimProcessingService {

    private static final Logger log =
            LoggerFactory.getLogger(ClaimProcessingService.class);

    private final PdfExtractionService pdfService;
    private final AIExtractionService aiService;
    private final ClaimValidator validator;
    private final RoutingService routingService;
    private final ClaimRepository claimRepository;

    public ClaimProcessingService(
            PdfExtractionService pdfService,
            AIExtractionService aiService,
            ClaimValidator validator,
            RoutingService routingService,
            ClaimRepository claimRepository){

        this.pdfService = pdfService;
        this.aiService = aiService;
        this.validator = validator;
        this.routingService = routingService;
        this.claimRepository = claimRepository;
    }

    //Single File Process
    public ClaimResponse process(MultipartFile file) throws Exception {

        long start = System.currentTimeMillis();

        String rawText = pdfService.extractText(file);

        ClaimResponse response =
                processInternal(rawText,file.getOriginalFilename());

        long totalTime = System.currentTimeMillis() - start;

        return new ClaimResponse(
                response.getFileName(),
                response.getClaimId(),
                totalTime,
                response.getAverageConfidence(),
                response.getExtractedFields(),
                response.getMissingFields(),
                response.getRecommendedRoute(),
                response.getReasoning()
        );
    }


    @Async("taskExecutor")
    public CompletableFuture<ClaimResponse> processAsync(MultipartFile file){

        try {
            return CompletableFuture.completedFuture(process(file));
        }
        catch (Exception e){
            log.error("Async processing failed for file {}",file.getOriginalFilename(),e);
            throw new RuntimeException(e);
        }
    }

    //Multi-File Process
    public List<ClaimResponse> processMultiple(List<MultipartFile> files){

        List<CompletableFuture<ClaimResponse>> futures =
                files.stream()
                        .map(this::processAsync)
                        .toList();

        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    //Text-based Process
    public ClaimResponse processText(String text){
        return processInternal(text,"TEXT_INPUT");
    }


    @Transactional
    private ClaimResponse processInternal(String rawText,String fileName){

        log.info("Processing started for file: {}", fileName);

        ClaimData claimData = aiService.extractFields(rawText);

        double avgConfidence =
                aiService.calculateAverageConfidence(
                        claimData.getConfidenceScores());

        List<String> missing =
                validator.validate(claimData);

        String route =
                routingService.route(claimData,missing);

        String reason =
                routingService.reason(claimData,missing);

        String claimId =
                ClaimIdGenerator.generateClaimId();


        ClaimEntity entity = new ClaimEntity();

        entity.setClaimId(claimId);
        entity.setPolicyNumber(claimData.getPolicyNumber());
        entity.setPolicyHolderName(claimData.getPolicyHolderName());
        entity.setClaimType(claimData.getClaimType());
        entity.setLocation(claimData.getLocation());
        entity.setIncidentDate(claimData.getIncidentDate());
        entity.setEstimatedDamage(claimData.getEstimatedDamage());
        entity.setAverageConfidence(avgConfidence);
        entity.setRoute(route);

        // FINAL STATE ONLY
        entity.setStatus(ClaimStatus.PROCESSED);

        claimRepository.save(entity);

        log.info("Processing completed for ClaimId: {}",claimId);

        return new ClaimResponse(
                fileName,
                claimId,
                0,
                avgConfidence,
                claimData,
                missing,
                route,
                reason
        );
    }
}

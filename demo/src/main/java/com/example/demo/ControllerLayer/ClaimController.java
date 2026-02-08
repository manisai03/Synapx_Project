package com.example.demo.ControllerLayer;

import com.example.demo.DTO.ClaimResponse;
import com.example.demo.ServiceLayer.ClaimProcessingService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimProcessingService service;

    public ClaimController(ClaimProcessingService service) {
        this.service = service;
    }

    // ----------------------------------------------------
    // SINGLE FILE FNOL PROCESSING
    // ----------------------------------------------------
    @PostMapping(
            value = "/process",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ClaimResponse> process(
            @RequestParam("file") MultipartFile file) throws Exception {

        return ResponseEntity.ok(service.process(file));
    }

    // ----------------------------------------------------
    // MULTI FILE FNOL PROCESSING
    // ----------------------------------------------------
    @PostMapping(
            value = "/process-multiple",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ClaimResponse>> processMultiple(
            @RequestParam("files") MultipartFile[] files) {

        return ResponseEntity.ok(
                service.processMultiple(List.of(files))
        );
    }

    // ----------------------------------------------------
    // TEXT BASED FNOL PROCESSING
    // ----------------------------------------------------
    @PostMapping(
            value = "/process-text",
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ClaimResponse> processText(
            @RequestBody String text) {

        return ResponseEntity.ok(service.processText(text));
    }
}

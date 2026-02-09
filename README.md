🚀 Autonomous Insurance Claims Processing Agent (Lite Version)

👨‍💻 Candidate: Somala Mani Sai Reddy

Role Applied: Junior Software Engineer – Synapx

Primary Focus: Backend System Design & FNOL Workflow Automation

📌 Project Overview:

This project is a lightweight autonomous backend system designed to process FNOL (First Notice of Loss) insurance claims. The system simulates real-world insurance claim ingestion pipelines by automatically extracting, validating, scoring, and routing insurance claims from unstructured inputs such as PDFs or text.

The system emphasizes:

        • Clean backend architecture
        • Explainable automation logic
        • High maintainability
        • Real-world scalability thinking

• Problem Statement:

Insurance companies receive FNOL documents in various unstructured formats like scanned PDFs and emails. Manual processing introduces:

            • Human errors
            • Processing delays
            • Inconsistent decision routing
            • Lack of traceability
This system automates FNOL processing by:

    • Extracts key claim fields
    • Identifies missing or inconsistent information
    • Applies confidence scoring
    • Routes claims automatically (Fast Track / Manual Review / Specialist Queue)
    • Persists claim lifecycle state

• System Architecture:
-> High-Level Architecture,

              Client (Postman / UI)
                      │
                      ▼
            REST Controller (ClaimController)
                      │
                      ▼
            ClaimProcessingService
                      │
               ┌──────┼─────────┐
               ▼      ▼         ▼
              PDF     AI       Validator
              Service Extractor Service
                      │
                      ▼
                Routing Service
                      │
                      ▼
                JPA Repository
                      |
                      ▼ 
                  H2-Database
• Design Principles:

                -> Layered Architecture
                -> Single Responsibility Per Component
                -> Stateless Processing
                -> Config-Driven Business Rules
                -> Explainable Automation Logic
                -> Extensibility for ML Integration

• Technology Stack:

              Layer	                Technology
            • Language	      -      Java 17
            • Backend	      -      Spring Boot
            • API	          -      REST (JSON)
            • Database	      -      H2 (In-memory)
            • ORM	          -      Spring Data JPA
            • Async	          -      CompletableFuture
            • PDF Parsing	  -      Apache PDFBox
            • Docs	          -      Springdoc OpenAPI
            • Build	          -      Maven

• FNOL Processing Sequence Flow

                1.Client uploads FNOL document (PDF / Text)
                2.Controller receives request
                3.ClaimProcessingService orchestrates workflow
                4.PDF text extraction executed
                5.AIExtractionService extracts structured data
                6.Validator checks mandatory fields
                7.RoutingService determines claim processing queue
                8.Claim persisted to database
                9.Response returned to client

• Functional Features:

1.Input Types Supported:

    • Text-based FNOL input (text/plain)
    • Single PDF upload
    • Multiple PDF uploads (parallel processing)

2.Field Extraction

Extracted fields:
    
    • Policy Number
    • Policy Holder Name
    • Incident Date
    • Location
    • Claim Type
    • Estimated Damage
    • Full Description

3.Confidence Scoring:

    • Each extracted field receives a confidence score (0–100).
    • Average confidence is calculated per claim.

4.Validation:

    • Mandatory fields validation
    • Missing fields identified
    • Returned in API response

5.Routing Logic:

      Condition	                      Route
    • Missing mandatory fields   -    Manual Review
    • Injury claim	             -    Specialist Queue
    • Damage ≤ 25,000	         -    Fast Track
    • Otherwise                  -    Manual Review

6.Claim Lifecycle:

• Each claim progresses through:

            CREATED → VALIDATED → ROUTED → PROCESSED
• Stored in the database for traceability.

📂 Project Layers Explanation:

1.ControllerLayer:

   • Responsibility:

            • Handles incoming HTTP requests and returns API responses.
            • Implemented Features
            • Single FNOL file processing
            • Multiple FNOL file processing
            • Text input FNOL processing
• Key File: " ClaimController "

• Endpoints:

              Endpoint	                            Description
            • /api/claims/process	          -     Single PDF Upload
            • /api/claims/process-multiple    -     Multi File Upload
            • /api/claims/process-text	      -     Text Based Processing

2.Service Layer:

• Responsibility: Contains business logic and orchestration.

• Services Implemented:

1️.ClaimProcessingService

            • Main orchestration engine
            • Calls extraction, validation, routing, and persistence
            • Supports async multi‑file processing

2️.AIExtractionService:

        • Uses Regex‑based NLP simulation
        • Extracts structured FNOL fields
        • Generates confidence scores

3️.PdfExtractionService:

   • Extracts raw text using Apache PDFBox

4️.RoutingService:

• Implements rule‑based workflow routing:

              Condition	                     Route
            • Missing Mandatory Fields	-    Manual Review
            • Fraud Keyword Detection	-    Investigation
            • Injury Claim Type	        -    Specialist Queue
            • Damage < 25000	        -    Fast Track
            • Others	                -    Standard Processing

3.Validation Layer:

   • ClaimValidator: Ensures mandatory FNOL fields exist.

        • Policy Number        • Policy Holder Name
        • Incident Date        • Location
        • Estimated Damage     • Claim Type

4.Repository Layer:

   • ClaimRepository: Uses Spring Data JPA for database persistence.

5. Entity Layer:

   • ClaimEntity: Represents stored claim data including.

            • Claim metadata
            • Confidence score
            • Routing decision
            • Lifecycle state
            • Timestamp tracking

6. DTO Layer:

   • ClaimResponse: Standardized response object returned to API clients.

            • Extracted fields
            • Missing field report
            • Routing decision
            • Confidence score
            • Processing time
   
7.Config Layer:

   • AsyncConfig: Enables multi‑threaded file processing.

   • FraudConfig: Externalizes fraud detection keywords.
   
8.Exception Handling:

   • GlobalExceptionHandler: Provides standardized error response structure.


• AI / Automation Approach:

   -> Instead of using heavy ML models, this project uses:

            • Regex‑driven information extraction
            • Confidence scoring logic
            • Rule‑based decision engine

   -> This approach keeps the system:

            • Lightweight
            • Explainable
            • Fast
            • Easily extensible to ML models later

• Confidence Scoring Logic:

        • Each extracted field receives score (0-100)
        • Missing fields assigned 0
        • Average confidence calculated per claim
This helps simulate real document extraction confidence metrics.

• Async Processing:

   -> Multi‑file FNOL uploads are processed concurrently using:

            -> " @Async + ThreadPoolTaskExecutor "
Benefits:

• Improved throughput
• Real‑world scalability simulation


• Database Design:

   -> Database: H2 (In‑Memory)

Stores:

        • Claim Metadata
        • Routing Decision
        • Confidence Score
        • Claim Lifecycle State


• Performance Considerations:

        • Parallel file processing via thread pools
        • Stateless service enabling horizontal scalability
        • Lightweight H2 database for fast evaluation
        • Configurable fraud detection rules


• Testing Strategy:

   -> Testing is implemented using:

                    • JUnit 5
                    • Mockito
                    • MockMvc

-> Test Coverage Includes:
                
                  Test Type	                 Coverage
                • Unit Testing	        -    Validation + Extraction
                • Service Testing	    -    Claim Processing Flow
                • Controller Testing	-    REST API Behaviour
                • Integration Testing	-    Spring Context Loading


• API Endpoints:

   -> Process Text FNOL,

            • POST - /api/claims/process-text
            • Content-Type: text/plain
   -> Process Single File,

            • POST - /api/claims/process
            • Form-Data: file
   -> Process Multiple Files,

            • POST - /api/claims/process-multiple
            • Form-Data: files[]

            
• Sample Output:

                " {
                "fileName": "TEXT_INPUT",
                "claimId": "CLM-8ca6a5a7",
                "processingTimeMs": 0,
                "averageConfidence": 90.0,
                "extractedFields": {
                "policyNumber": "POL-88888",
                "policyHolderName": "Arjun Reddy",
                "incidentDate": "02/02/2026",
                "location": "Chennai",
                "estimatedDamage": 50000,
                "claimType": "Injury"
                },
                "missingFields": [],
                "recommendedRoute": "Specialist Queue",
                "reasoning": "Injury claims require specialist review."
                } "
                

• Project Structure:

                demo
                ├── src
                    ├── com.example.demo
                        ├── ControllerLayer
                            ├── ClaimController
                        ├── ServiceLayer
                            ├── AIExtractionService
                            ├── ClaimProcessingService
                            ├── PdfExtractionService
                            ├── RoutingService
                        ├── Validation
                            ├── ClaimValidator
                        ├── Repository
                            ├── ClaimRepository
                        ├── Entity
                            ├── ClaimEntity
                            ├── ClaimStatus
                        ├── DTO
                            ├── ClaimResponse
                        ├── Util
                            ├── ClaimIdGenerator
                        ├── Exception
                            ├── ErrorResponse
                            ├── GlobalExeptionHandler
                        └── config
                            ├── AsyncConfig
                            ├── FraudConfig


• Summary:

This FNOL Processing Agent demonstrates strong backend engineering fundamentals with a clear focus on maintainability, scalability, and explainable automation logic. The architecture is intentionally designed to simulate real enterprise insurance claim processing systems while remaining lightweight and extensible.

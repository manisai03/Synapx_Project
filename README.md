🚀 Autonomous Insurance Claims Processing Agent (Lite Version)

👨‍💻 Candidate: Somala Mani Sai Reddy

Role Applied: Junior Software Engineer – Synapx

Tech Stack: Java, Spring Boot, H2 Database, PDFBox, Swagger, JUnit, Mockito

📌 Overview

This project is a lite autonomous backend system for processing FNOL (First Notice of Loss) insurance claims. It simulates how real-world insurance platforms automatically extract claim details, validate completeness, calculate confidence, and route claims to the appropriate processing queue.
The focus is clarity over complexity, strong backend fundamentals, and clean problem breakdown — exactly as expected for a Junior Software Engineer role.

🎯 Problem Statement:

Insurance FNOL documents often arrive as unstructured text or PDFs. Manual processing is slow and error-prone.
This system:

    • Extracts key claim fields
    • Identifies missing or inconsistent information
    • Applies confidence scoring
    • Routes claims automatically (Fast Track / Manual Review / Specialist Queue)
    • Persists claim lifecycle state

🧱 System Architecture:
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

       -> Layered architecture for clarity
       -> Single responsibility per service
       -> Stateless processing
       -> Extensible rule-based logic

Technology Stack:

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

Functional Features:

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

            • Claim lifecycle state
            • Average confidence score
            • Routing decision

6. DTO Layer:

   • ClaimResponse: Standardized response object returned to API clients.
7.Config Layer:

   • AsyncConfig: Enables multi‑threaded file processing.

   • FraudConfig: Externalizes fraud detection keywords.
8.Exception Handling:

   • GlobalExceptionHandler: Provides standardized error response structure.

• AI / Automation Approach:

   • Instead of using heavy ML models, this project uses:

            • Regex‑driven information extraction
            • Confidence scoring logic
            • Rule‑based decision engine

  • This approach keeps the system:

            • Lightweight
            • Explainable
            • Fast
            • Easily extensible to ML models later

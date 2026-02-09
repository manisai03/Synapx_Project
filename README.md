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
Layer	Technology
Language	Java 17
Backend	Spring Boot
API	REST (JSON)
Database	H2 (In-memory)
ORM	Spring Data JPA
Async	CompletableFuture
PDF Parsing	Apache PDFBox
Docs	Springdoc OpenAPI
Build	Maven

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

4.Validation

    • Mandatory fields validation
    • Missing fields identified
    • Returned in API response

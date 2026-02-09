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
